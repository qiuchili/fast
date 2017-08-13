// automatically generated by the FlatBuffers compiler, do not modify

package _fast._Delta;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Diff extends Table {
  public static Diff getRootAsDiff(ByteBuffer _bb) { return getRootAsDiff(_bb, new Diff()); }
  public static Diff getRootAsDiff(ByteBuffer _bb, Diff obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; }
  public Diff __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public int type() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public _fast._Delta._Diff.Anonymous2 delta() { return delta(new _fast._Delta._Diff.Anonymous2()); }
  public _fast._Delta._Diff.Anonymous2 delta(_fast._Delta._Diff.Anonymous2 obj) { int o = __offset(6); return o != 0 ? obj.__assign(__indirect(o + bb_pos), bb) : null; }

  public static int createDiff(FlatBufferBuilder builder,
      int type,
      int deltaOffset) {
    builder.startObject(2);
    Diff.addDelta(builder, deltaOffset);
    Diff.addType(builder, type);
    return Diff.endDiff(builder);
  }

  public static void startDiff(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addType(FlatBufferBuilder builder, int type) { builder.addInt(0, type, 0); }
  public static void addDelta(FlatBufferBuilder builder, int deltaOffset) { builder.addOffset(1, deltaOffset, 0); }
  public static int endDiff(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

