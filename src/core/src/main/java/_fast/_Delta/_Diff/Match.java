// automatically generated by the FlatBuffers compiler, do not modify

package _fast._Delta._Diff;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Match extends Table {
  public static Match getRootAsMatch(ByteBuffer _bb) { return getRootAsMatch(_bb, new Match()); }
  public static Match getRootAsMatch(ByteBuffer _bb, Match obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; }
  public Match __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public int src() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int dst() { int o = __offset(6); return o != 0 ? bb.getInt(o + bb_pos) : 0; }

  public static int createMatch(FlatBufferBuilder builder,
      int src,
      int dst) {
    builder.startObject(2);
    Match.addDst(builder, dst);
    Match.addSrc(builder, src);
    return Match.endMatch(builder);
  }

  public static void startMatch(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addSrc(FlatBufferBuilder builder, int src) { builder.addInt(0, src, 0); }
  public static void addDst(FlatBufferBuilder builder, int dst) { builder.addInt(1, dst, 0); }
  public static int endMatch(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

