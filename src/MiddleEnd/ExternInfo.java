package MiddleEnd;

public class ExternInfo {
    public static String targetDatalayout = "\"e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128\"";
    public static String targetTriple = "\"x86_64-pc-linux-gnu\"";

    public static String getExternInfo() {
        var ret = "target datalayout = " + targetDatalayout;
        ret += '\n';
        ret += "target triple = " + targetTriple;
        ret += '\n';
        return ret;
    }

}
