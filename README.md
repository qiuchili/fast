[![Build Status](https://travis-ci.org/yijunyu/fast.svg?branch=master)](https://travis-ci.org/yijunyu/fast)
# `fast` -- Flattening Abstract Syntax Trees
This tool flattens code structures of abstract syntax trees (ASTs) as binary so that it is more efficient to load and save them between memory and disk, avoiding re-parsing altogether.

## Synopsis

```
$ fast [-acdehpsSt] $input [$output]
```

## Description
The use of the tool is fairly simple. It accepts file arguments on the command line, namely input and output.
If the second file is unspecified, the output will be directed to the standard output.

The following options are available:

     -a      Use Antlr's AST instead of srcML's, currently we support .smali for Android assembler.

     -c      Load the file only, no output.

     -d      Decode the textual representation from protobuf AST input.

     -e      Encode the protobuf binary AST from the textual input.

     -h      Print this help message.

     -p      Position (line, column) is added to source code elements.

     -s      Invoke srcSlice. This option turns on the -p option.

     -S      Invoke modified srcSlice to use the binary AST directly.

## Usage
Here are a few examples:

### Convert from source code to SrcML

  $ fast [/usr/local/share/Hello.java](test/Hello.java) [Hello.xml](test/Hello.xml)

  $ fast [/usr/local/share/example.cc](test/example.cc) [example.xml](test/example.xml)

### Convert SrcML back to source code

  $ fast [Hello.xml](test/Hello.xml) [Hello.java](test/Hello.java)

### Convert SrcML to binary AST

  $ fast [Hello.xml](test/Hello.xml) [Hello.pb](test/Hello.pb)

  $ fast [example.xml](test/example.xml) [example.fbs](test/example.fbs)

### Convert binary AST back to SrcML

  $ fast [Hello.pb](test/Hello.pb) [Hello.xml](test/Hello.xml)

  $ fast [example.fbs](test/example.fbs) [example.xml](test/example.xml)

### Convert binary AST back to source code

  $ fast [Hello.pb](test/Hello.pb) [Hello.java](test/Hello.java)
  
  $ fast [example.fbs](test/example.fbs) [example.cc](test/example.cc)

### Print the textual representation of the protocol buffer using the generated fAST schema.

  $ fast -d [Hello.pb](test/Hello.pb)

  $ fast -d [Hello.pb](test/Hello.pb) [Hello.txt](test/Hello.txt)

### Translate the textual representations into the corresponding protobuf file. 

  $ fast -e [Hello.txt](test/Hello.txt) [Hello.pb](test/Hello.pb)
  
### Keep element positions in binary AST

  $ fast -p [Hello.java](test/Hello.java) [Hello.pb](test/Hello.position.pb)
  
  $ fast -p [Hello.pb](test/Hello.position.pb) [Hello.xml](test/Hello.position.xml)

These commands will keep the line/column positions of the code elements in the
corresponding binary and XML documents.  Note that if "-p" option is not
provided, even if the protobuf document has the code elements' position
information, they will be omitted in the XML document.

### Forward slice source code

  $ fast -p [test/example.cc](test/example.cc) [example.pb](test/example.position.pb)
  
  $ fast -s [example.pb](test/example.position.pb) > [example.slice](test/example.slice)
  
  $ fast -S [example.pb](test/example.position.pb) > [example.slice](test/example.slice)
  
  $ fast -p [test/example.cc](test/example.cc) [example.fbs](test/example.position.fbs)
	
  $ fast -s [example.fbs](test/example.position.fbs) > [example.slice](test/example.slice)
	
  $ fast -S [example.fbs](test/example.position.fbs) > [example.slice](test/example.slice)
	
  $ fast -s [test/example.cc](test/example.cc) > [example.slice](test/example.slice)

  $ fast -s [Hello.java](test/Hello.java) > [Hello.slice](test/Hello.slice)

These commands perform forward program slicing on the source code using the srcSlice tool. 
The modified srcSlice tool can replace parsing the srcML with loading the binary AST, which is much more efficient.

### Convert ANTLR3-based language to binary AST
  $ fast -a [/usr/local/share/DuplicateVirtualMethods.smali](test/DuplicateVirtualMethods.smali) [DuplicateVirtualMethods.pb](test/DuplicateVirtualMethods.pb)
  
  $ fast -d [DuplicateVirtualMethods.pb](test/DuplicateVirtualMethods.pb) > [DuplicateVirtualMethods.txt](test/DuplicateVirtualMethods.txt)
  
  $ fast -a [DuplicateVirtualMethods.pb](test/DuplicateVirtualMethods.pb) [DuplicateVirtualMethods.xml](test/DuplicateVirtualMethods.xml)

These commands convert between Android's smali representation and our protobuf representations. The first command converts SMALI code into a binary AST of
the structural information; the second command decodes the binary AST into textual form; the third command marks up the original SMALI code with XML tags
taken from the binary AST, hence making it similar to SrcML structures (albeit following the underlying ANTLR3 schema). 

### Convert GumTreeDiff editing scripts to binary representations

  $ fast -a [/usr/local/share/DuplicateVirtualMethods.smali](test/DuplicateVirtualMethods.smali) [/usr/local/share/DuplicateVirtualMethods-v2.smali](test/DuplicateVirtualMethods-v2.smali) [DuplicateVirtualMethods-diff.pb](test/DuplicateVirtualMethods-diff.pb)

The command computes the GumTreeDiff editing scripts between the two smali input files and saves the scripts into protobuf structure. The protobuf schema has been extended to record the tree-based delta script. 

## Performance
In the benchmarks folder if you check out from the `git` repository, you will see much larger examples where `fast` has been applied to speed up the parsing process by up to 100 times. 

![Benchmarks 1. Most popular projects in 5 programming languages](https://github.com/yijunyu/fast/raw/master/benchmarks/benchmarks1.png "The projects are selected from those with the most stars on GitHub.") ![Benchmarks 2. Bug related commits in evolving artefacts in Java](https://github.com/yijunyu/fast/raw/master/benchmarks/benchmarks2.png "The projects are selected from academic studies on the bug localization problems.")

That's part of the reasons why we call it "fast".

A report on performance evaluation will be placed under the `doc/` subfolder.

## License

`fast` is distributed under GPL. See license.txt for details.

## Dependencies
The current binary implementation is based on `protobuf` and `flatbuffers`. 

If the source code is one of the five programming languages: Java, C, C++, C#,
Objective C, we use `srcml` to parse code to XML and
[`rapidxml`](https://github.com/dwd/rapidxml) which parses XML documents.
In these cases, the binary AST is standalone, which has all the concrete text 
of the original code.

Otherwise, if the source code is based on ANTLR or other tools such as JDT to
create AST, we use `gumtree` to parse code into its ITree structure, then
translates them into the binary form. Note that in these cases, the binary form
stores primarily the AST that can trace back to concrete syntax of the original
source code. 

To get it working, it has dependencies on the following software:

* [srcML](http://www.srcml.org/)

* [protobuf](https://github.com/google/protobuf)

* [flatbuffers](https://github.com/google/flatbuffers)

* [gumtreediff](https://github.com/GumTreeDiff/gumtree)

* [smali](https://github.com/JesusFreke/smali)

* [ANTLR3](https://github.com/antlr/antlr3)

If they aren't installed, the following commands will get them installed:
### MacOSX using [Homebrew](https://brew.sh/) 
```
	$ /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
	$ brew tap yijunyu/fast
	$ brew install fast
```
The 1st line is optional, it will install Homebrew if you hadn't got it.

#### Caveat, reported and fixed by Bram Adams
If you are installing the tool on Yosemite, are the system complains about 
```
dyld: Library not loaded: @@HOMEBREW_PREFIX@@/opt/LibArchive/lib/libarchive.13.dylib
  Referenced from: /usr/local/bin/srcml
  Reason: image not found
```
You can fix this problem by the following workaround using `install_name_tool`:
```
sudo install_name_tool -change @@HOMEBREW_PREFIX@@/opt/LibArchive/lib/libarchive.13.dylib /usr/local/Cellar/libarchive/3.3.1/lib/libarchive.13.dylib /usr/local/bin/srcml
```

### Ubuntu Linux using Debian packages:
```
	$ sudo apt-get install apt-transport-https
	$ sudo su -
	$ echo deb http://yijunyu.github.io/ubuntu ./ >> /etc/apt/sources.list
	$ apt-get update
	$ apt-get install fast
```
Specifically, the 1st line is to support HTTPS transport protocol for the repository on github.io; 
the 2nd line is to update the list of repositories on your machine.
