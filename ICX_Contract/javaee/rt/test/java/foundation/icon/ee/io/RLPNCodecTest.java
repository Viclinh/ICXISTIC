/*
 * Copyright 2023 ICON Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package foundation.icon.ee.io;

import java.math.BigInteger;

public class RLPNCodecTest {
    public interface Assertion {
        void assertCodingEquals(String exp, boolean v);

        void assertCodingEquals(String exp, byte v);

        void assertCodingEquals(String exp, short v);

        void assertCodingEquals(String exp, char v);

        void assertCodingEquals(String exp, int v);

        void assertCodingEquals(String exp, float v);

        void assertCodingEquals(String exp, long v);

        void assertCodingEquals(String exp, double v);

        void assertCodingEquals(String exp, BigInteger v);

        void assertCodingEquals(String exp, String v);

        void assertCodingEquals(String exp, byte[] v);

        void assertListCodingEquals(String exp, byte[] v);
    }

    public static String repeat(String s, int n) {
        var sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void testRLPNSimpleSmall(RLPNCodecTest.Assertion a) {
        a.assertCodingEquals("00", false);
        a.assertCodingEquals("01", true);

        a.assertCodingEquals("00", (byte) 0);
        a.assertCodingEquals("01", (byte) 1);
        a.assertCodingEquals("79", (byte) 0x79);
        a.assertCodingEquals("8180", (byte) 0x80);
        a.assertCodingEquals("81ff", (byte) 0xff);

        a.assertCodingEquals("00", (short) 0);
        a.assertCodingEquals("01", (short) 1);
        a.assertCodingEquals("79", (short) 0x79);
        a.assertCodingEquals("820080", (short) 0x80);
        a.assertCodingEquals("827fff", (short) 0x7fff);
        a.assertCodingEquals("828000", (short) 0x8000);
        a.assertCodingEquals("8180", (short) 0xff80);
        a.assertCodingEquals("81ff", (short) 0xffff);

        a.assertCodingEquals("00", (char) 0);
        a.assertCodingEquals("01", (char) 1);
        a.assertCodingEquals("79", (char) 0x79);
        a.assertCodingEquals("820080", (char) 0x80);
        a.assertCodingEquals("827fff", (char) 0x7fff);
        a.assertCodingEquals("83008000", (char) 0x8000);
        a.assertCodingEquals("8300ffff", (char) 0xffff);

        a.assertCodingEquals("00", 0);
        a.assertCodingEquals("01", 1);
        a.assertCodingEquals("79", 0x79);
        a.assertCodingEquals("820080", 0x80);
        a.assertCodingEquals("827fff", 0x7fff);
        a.assertCodingEquals("83008000", 0x8000);
        a.assertCodingEquals("837fffff", 0x7fffff);
        a.assertCodingEquals("8400800000", 0x800000);
        a.assertCodingEquals("847fffffff", 0x7fffffff);
        a.assertCodingEquals("8480000000", 0x80000000);
        a.assertCodingEquals("84ff7f0000", 0xff7f0000);
        a.assertCodingEquals("83800000", 0xff800000);
        a.assertCodingEquals("83ff7f00", 0xffff7f00);
        a.assertCodingEquals("828000", 0xffff8000);
        a.assertCodingEquals("82ff7f", 0xffffff7f);
        a.assertCodingEquals("8180", 0xffffff80);
        a.assertCodingEquals("81ff", 0xffffffff);

        a.assertCodingEquals("8401020304", Float.intBitsToFloat(0x01020304));

        a.assertCodingEquals("00", 0L);
        a.assertCodingEquals("01", 1L);
        a.assertCodingEquals("79", 0x79L);
        a.assertCodingEquals("820080", 0x80L);
        a.assertCodingEquals("827fff", 0x7fffL);
        a.assertCodingEquals("83008000", 0x8000L);
        a.assertCodingEquals("837fffff", 0x7fffffL);
        a.assertCodingEquals("8400800000", 0x800000L);
        a.assertCodingEquals("847fffffff", 0x7fffffffL);
        a.assertCodingEquals("850080000000", 0x80000000L);
        a.assertCodingEquals("857fffffffff", 0x7fffffffffL);
        a.assertCodingEquals("86008000000000", 0x8000000000L);
        a.assertCodingEquals("867fffffffffff", 0x7fffffffffffL);
        a.assertCodingEquals("8700800000000000", 0x800000000000L);
        a.assertCodingEquals("877fffffffffffff", 0x7fffffffffffffL);
        a.assertCodingEquals("880080000000000000", 0x80000000000000L);
        a.assertCodingEquals("887fffffffffffffff", 0x7fffffffffffffffL);
        a.assertCodingEquals("888000000000000000", 0x8000000000000000L);
        a.assertCodingEquals("88ff7f000000000000", 0xff7f000000000000L);
        a.assertCodingEquals("8780000000000000", 0xff80000000000000L);
        a.assertCodingEquals("87ff7f0000000000", 0xffff7f0000000000L);
        a.assertCodingEquals("86800000000000", 0xffff800000000000L);
        a.assertCodingEquals("86ff7f00000000", 0xffffff7f00000000L);
        a.assertCodingEquals("858000000000", 0xffffff8000000000L);
        a.assertCodingEquals("85ff7f000000", 0xffffffff7f000000L);
        a.assertCodingEquals("8480000000", 0xffffffff80000000L);
        a.assertCodingEquals("84ff7f0000", 0xffffffffff7f0000L);
        a.assertCodingEquals("83800000", 0xffffffffff800000L);
        a.assertCodingEquals("83ff7f00", 0xffffffffffff7f00L);
        a.assertCodingEquals("828000", 0xffffffffffff8000L);
        a.assertCodingEquals("82ff7f", 0xffffffffffffff7fL);
        a.assertCodingEquals("8180", 0xffffffffffffff80L);
        a.assertCodingEquals("81ff", 0xffffffffffffffffL);

        a.assertCodingEquals("880102030405060708", Double.longBitsToDouble(0x0102030405060708L));

        a.assertCodingEquals("00", BigInteger.valueOf(0));
        a.assertCodingEquals("01", BigInteger.valueOf(1));
        a.assertCodingEquals("820080", BigInteger.valueOf(0x80));
        a.assertCodingEquals("b701" + repeat("00", 54), new BigInteger("01" + repeat("00", 54), 16));
        a.assertCodingEquals("81ff", BigInteger.valueOf(-1));

        a.assertCodingEquals("80", "");
        a.assertCodingEquals("40", "@");
        a.assertCodingEquals("824040", "@@");

        a.assertCodingEquals("80", new byte[0]);
        a.assertCodingEquals("00", new byte[1]);
        a.assertCodingEquals("b7" + repeat("00", 55), new byte[55]);
        a.assertCodingEquals("b838" + repeat("00", 56), new byte[56]);
        a.assertCodingEquals("b8ff" + repeat("00", 255), new byte[255]);
        a.assertCodingEquals("b90100" + repeat("00", 256), new byte[256]);
        a.assertCodingEquals("ba010000" + repeat("00", 1 << 16), new byte[1 << 16]);

        a.assertListCodingEquals("c0", null);
        a.assertListCodingEquals("c100", new byte[1]);
        a.assertListCodingEquals("c3820000", new byte[2]);
        a.assertListCodingEquals("f7b6" + repeat("00", 54), new byte[54]);
        a.assertListCodingEquals("f838b7" + repeat("00", 55), new byte[55]);
        a.assertListCodingEquals("f90100b8fe" + repeat("00", 254), new byte[254]);
        a.assertListCodingEquals("fa010004ba010000" + repeat("00", 1 << 16), new byte[1 << 16]);
    }

    public static void testRLPNSimple(RLPNCodecTest.Assertion a) {
        testRLPNSimpleSmall(a);
        a.assertCodingEquals("bb01000000" + repeat("00", 1 << 24), new byte[1 << 24]);
        a.assertListCodingEquals("fb01000005bb01000000" + repeat("00", 1 << 24), new byte[1 << 24]);
    }
}
