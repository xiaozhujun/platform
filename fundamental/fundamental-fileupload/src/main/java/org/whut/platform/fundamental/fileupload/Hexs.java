package org.whut.platform.fundamental.fileupload;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.whut.platform.fundamental.logger.PlatformLogger;

import java.nio.charset.Charset;

public final class Hexs {

	private static final PlatformLogger LOGGER = PlatformLogger
			.getLogger(Hexs.class);

	private Hexs() {
		throw new Error("Utility classes should not instantiated!");
	}

	// FIXME this method may be error
	public static String stringToHexString(String strPart) {
		return String.valueOf(Hex.encodeHex(strPart.getBytes(Charset
				.defaultCharset())));
	}

	public static String encode(String str) {
		return String.valueOf(Hex.encodeHex(
				str.getBytes(Charset.defaultCharset()), false));
	}

	public static String decode(String bytes) {
		try {
			return new String(Hex.decodeHex(bytes.toCharArray()),
					Charset.defaultCharset());
		} catch (DecoderException e) {
			LOGGER.error("Decode String error:{}", e);
			return StringUtils.EMPTY;
		}
	}

	public static byte[] hexString2Bytes(String src) {
		try {
			return Hex.decodeHex(src.toCharArray());
		} catch (DecoderException e) {
			LOGGER.error("Decode byte[] error:{}", e);
			return ArrayUtils.EMPTY_BYTE_ARRAY;
		}
	}
}
