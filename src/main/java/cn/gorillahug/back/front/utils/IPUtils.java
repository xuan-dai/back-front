package cn.gorillahug.back.front.utils;

import java.util.regex.Pattern;

public class IPUtils {

    private int mask;
    private static final String IPV4_REGEX = "((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})";
    public static final int IP_A_TYPE = 1;
    public static final int IP_B_TYPE = 2;
    public static final int IP_C_TYPE = 3;
    public static final int IP_OTHER_TYPE = 4;
    private static int[] IpATypeRange = new int[2];
    private static int[] IpBTypeRange;
    private static int[] IpCTypeRange;
    private static int DefaultIpAMask;
    private static int DefaultIpBMask;
    private static int DefaultIpCMask;

    public IPUtils() {
        this.mask = getIpV4Value("255.255.255.0");
    }

    public IPUtils(String masks) {
        this.mask = getIpV4Value(masks);
        if (this.mask == 0) {
            throw new UnknownError();
        }
    }

    public int getMask() {
        return this.mask;
    }

    public boolean checkSameSegment(String ip1, String ip2) {
        return checkSameSegment(ip1, ip2, this.mask);
    }

    public static boolean checkSameSegment(String ip1, String ip2, int mask) {
        if (!ipV4Validate(ip1)) {
            return false;
        } else if (!ipV4Validate(ip2)) {
            return false;
        } else {
            int ipValue1 = getIpV4Value(ip1);
            int ipValue2 = getIpV4Value(ip2);
            return (mask & ipValue1) == (mask & ipValue2);
        }
    }

    public static boolean checkSameSegmentByDefault(String ip1, String ip2) {
        int mask = getDefaultMaskValue(ip1);
        return checkSameSegment(ip1, ip2, mask);
    }

    public int getSegmentValue(String ipV4) {
        int ipValue = getIpV4Value(ipV4);
        return this.mask & ipValue;
    }

    public static int getSegmentValue(String ip, int mask) {
        int ipValue = getIpV4Value(ip);
        return mask & ipValue;
    }

    public static boolean ipV4Validate(String ipv4) {
        return ipv4Validate(ipv4, "((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})");
    }

    private static boolean ipv4Validate(String addr, String regex) {
        return addr != null && Pattern.matches(regex, addr.trim());
    }

    public static int compareIpV4s(String ip1, String ip2) {
        int result = 0;
        int ipValue1 = getIpV4Value(ip1);
        int ipValue2 = getIpV4Value(ip2);
        if (ipValue1 > ipValue2) {
            result = -1;
        } else if (ipValue1 <= ipValue2) {
            result = 1;
        }

        return result;
    }

    public static int checkIpV4Type(String ipV4) {
        int inValue = getIpV4Value(ipV4);
        if (inValue >= IpCTypeRange[0] && inValue <= IpCTypeRange[1]) {
            return 3;
        } else if (inValue >= IpBTypeRange[0] && inValue <= IpBTypeRange[1]) {
            return 2;
        } else {
            return inValue >= IpATypeRange[0] && inValue <= IpATypeRange[1] ? 1 : 4;
        }
    }

    public static int getDefaultMaskValue(String anyIpV4) {
        int checkIpType = checkIpV4Type(anyIpV4);
        int maskValue;
        switch(checkIpType) {
            case 1:
                maskValue = DefaultIpAMask;
                break;
            case 2:
                maskValue = DefaultIpBMask;
                break;
            case 3:
                maskValue = DefaultIpCMask;
                break;
            default:
                maskValue = DefaultIpCMask;
        }

        return maskValue;
    }

    public static String getDefaultMaskStr(String anyIp) {
        return trans2IpStr(getDefaultMaskValue(anyIp));
    }

    public static String trans2IpStr(int ipValue) {
        return (ipValue >> 24 & 255) + "." + (ipValue >> 16 & 255) + "." + (ipValue >> 8 & 255) + "." + (ipValue & 255);
    }

    public static String trans2IpV4Str(byte[] ipBytes) {
        return (ipBytes[0] & 255) + "." + (ipBytes[1] & 255) + "." + (ipBytes[2] & 255) + "." + (ipBytes[3] & 255);
    }

    public static int getIpV4Value(String ipOrMask) {
        byte[] addr = getIpV4Bytes(ipOrMask);
        int address1 = addr[3] & 255;
        address1 |= addr[2] << 8 & '\uff00';
        address1 |= addr[1] << 16 & 16711680;
        address1 |= addr[0] << 24 & -16777216;
        return address1;
    }

    public static byte[] getIpV4Bytes(String ipOrMask) {
        try {
            String[] addrs = ipOrMask.split("\\.");
            int length = addrs.length;
            byte[] addr = new byte[length];

            for(int index = 0; index < length; ++index) {
                addr[index] = (byte)(Integer.parseInt(addrs[index]) & 255);
            }

            return addr;
        } catch (Exception var5) {
            return new byte[4];
        }
    }

    static {
        IpATypeRange[0] = getIpV4Value("1.0.0.1");
        IpATypeRange[1] = getIpV4Value("126.255.255.254");
        IpBTypeRange = new int[2];
        IpBTypeRange[0] = getIpV4Value("128.0.0.1");
        IpBTypeRange[1] = getIpV4Value("191.255.255.254");
        IpCTypeRange = new int[2];
        IpCTypeRange[0] = getIpV4Value("192.168.0.0");
        IpCTypeRange[1] = getIpV4Value("192.168.255.255");
        DefaultIpAMask = getIpV4Value("255.0.0.0");
        DefaultIpBMask = getIpV4Value("255.255.0.0");
        DefaultIpCMask = getIpV4Value("255.255.255.0");
    }
}
