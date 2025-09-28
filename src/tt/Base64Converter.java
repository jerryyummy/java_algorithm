package tt;

public class Base64Converter {
    static String table = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static String encode(byte[] a){
        if (a == null || a.length == 0) return "";
        StringBuilder sb = new StringBuilder((a.length + 2) / 3 * 4);
        for (int i = 0; i < a.length; ){
            int b0 = a[i++] & 0xFF;
            int b1 = (i < a.length) ? (a[i++] & 0xFF) : -1;
            int b2 = (i < a.length) ? (a[i++] & 0xFF) : -1;

            sb.append(table.charAt(b0 >>> 2));
            sb.append(table.charAt( (b0 & 0x3) << 4 | Math.max(b1, 0) >>> 4));
            sb.append(b1>=0?table.charAt( (b1 & 0xF) << 2 | ((Math.max(b2, 0)) >>> 6)):'=' );
            sb.append(b2>=0?table.charAt(b2 & 0x3F) : '=');
        }
        return sb.toString();
    }

    public static byte[] decode(String s) {
        if (s == null || s.isEmpty()) return new byte[0];

        // 去空白
        StringBuilder t = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            t.append(c);
        }
        // 自动补齐到 4 的倍数（如果你始终补 '=', 这段也能保留无害）
        int r = t.length() % 4;
        if (r == 1) {
            t.append("===");
        }
        if (r == 2) {
            t.append("==");
        }
        if (r == 3) {
            t.append('=');
        }

        int len = t.length();
        int pad = (len >= 1 ? (t.charAt(len - 1) == '=' ? 1 : 0) + (t.charAt(len - 2) == '=' ? 1 : 0) + (t.charAt(len - 3) == '=' ? 1 : 0) : 0);
        byte[] out = new byte[(len / 4) * 3 - pad];
        int p = 0;

        for (int i = 0; i < len; i+=4) {
            int v0 = table.indexOf(t.charAt(i));
            int v1 = t.charAt(i + 1) == '=' ? -1:table.indexOf(t.charAt(i + 1));
            int v2 = t.charAt(i + 2) == '=' ? -1:table.indexOf(t.charAt(i + 2));
            int v3 = t.charAt(i + 3) == '=' ? -1:table.indexOf(t.charAt(i + 3));

            out[p++] = (byte) ((v0 << 2) | (v1 >>> 4));                         // 第1字节
            if (v2 >= 0) out[p++] = (byte) (((v1 & 0xF) << 4) | (v2 >>> 2));    // 第2字节
            if (v3 >= 0) out[p++] = (byte) (((v2 & 0x3) << 6) | v3);            // 第3字节

        }
        return out;
    }

    public static void main(String[] args) {
        String encoded = encode("a==".getBytes());
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + new String(decode(encoded)));
    }
}
