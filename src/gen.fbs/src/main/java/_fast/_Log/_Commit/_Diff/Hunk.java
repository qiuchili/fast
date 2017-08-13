// automatically generated by the FlatBuffers compiler, do not modify

package _fast._Log._Commit._Diff;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Hunk extends Table {
  public static Hunk getRootAsHunk(ByteBuffer _bb) { return getRootAsHunk(_bb, new Hunk()); }
  public static Hunk getRootAsHunk(ByteBuffer _bb, Hunk obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; }
  public Hunk __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public int fromLineno() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int fromColumn() { int o = __offset(6); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int toLineno() { int o = __offset(8); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int toColumn() { int o = __offset(10); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public String context() { int o = __offset(12); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer contextAsByteBuffer() { return __vector_as_bytebuffer(12, 1); }
  public _fast.Element element(int j) { return element(new _fast.Element(), j); }
  public _fast.Element element(_fast.Element obj, int j) { int o = __offset(14); return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null; }
  public int elementLength() { int o = __offset(14); return o != 0 ? __vector_len(o) : 0; }
  public _fast._Log._Commit._Diff._Hunk.ModLine mod(int j) { return mod(new _fast._Log._Commit._Diff._Hunk.ModLine(), j); }
  public _fast._Log._Commit._Diff._Hunk.ModLine mod(_fast._Log._Commit._Diff._Hunk.ModLine obj, int j) { int o = __offset(16); return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null; }
  public int modLength() { int o = __offset(16); return o != 0 ? __vector_len(o) : 0; }
  public _fast.Slices slice() { return slice(new _fast.Slices()); }
  public _fast.Slices slice(_fast.Slices obj) { int o = __offset(18); return o != 0 ? obj.__assign(__indirect(o + bb_pos), bb) : null; }

  public static int createHunk(FlatBufferBuilder builder,
      int from_lineno,
      int from_column,
      int to_lineno,
      int to_column,
      int contextOffset,
      int elementOffset,
      int modOffset,
      int sliceOffset) {
    builder.startObject(8);
    Hunk.addSlice(builder, sliceOffset);
    Hunk.addMod(builder, modOffset);
    Hunk.addElement(builder, elementOffset);
    Hunk.addContext(builder, contextOffset);
    Hunk.addToColumn(builder, to_column);
    Hunk.addToLineno(builder, to_lineno);
    Hunk.addFromColumn(builder, from_column);
    Hunk.addFromLineno(builder, from_lineno);
    return Hunk.endHunk(builder);
  }

  public static void startHunk(FlatBufferBuilder builder) { builder.startObject(8); }
  public static void addFromLineno(FlatBufferBuilder builder, int fromLineno) { builder.addInt(0, fromLineno, 0); }
  public static void addFromColumn(FlatBufferBuilder builder, int fromColumn) { builder.addInt(1, fromColumn, 0); }
  public static void addToLineno(FlatBufferBuilder builder, int toLineno) { builder.addInt(2, toLineno, 0); }
  public static void addToColumn(FlatBufferBuilder builder, int toColumn) { builder.addInt(3, toColumn, 0); }
  public static void addContext(FlatBufferBuilder builder, int contextOffset) { builder.addOffset(4, contextOffset, 0); }
  public static void addElement(FlatBufferBuilder builder, int elementOffset) { builder.addOffset(5, elementOffset, 0); }
  public static int createElementVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startElementVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addMod(FlatBufferBuilder builder, int modOffset) { builder.addOffset(6, modOffset, 0); }
  public static int createModVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startModVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addSlice(FlatBufferBuilder builder, int sliceOffset) { builder.addOffset(7, sliceOffset, 0); }
  public static int endHunk(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

