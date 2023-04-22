package com.kkwo.demo.util;

public class Ut {
	public static boolean empty(Object obj) {

		if (obj == null) {
			return true;
		}

		if (obj instanceof String == false) {
			return true;
		}

		String str = (String) obj;

		return str.trim().length() == 0;
	}

	public static String f(String format, Object... args) {
		return String.format(format, args);
	}

	/* javascript */
	public static String jsHistoryBack(String resultCode, String resultMsg) {
		return Ut.f("""
				<script>
					const resultMsg = '%s'.trim();
					if( resultMsg.length > 0 ){
						alert(resultMsg);
					}
					history.back();
				</script>
				""", resultMsg);
	}

	public static String jsReplace(String resultMsg, String uri) {
		if(resultMsg == null) {
			resultMsg = "";
		}
		if(uri == null) {
			uri = "/";
		}
		
		return Ut.f("""
				<script>
				const resultMsg = '%s'.trim();
				if( resultMsg.length > 0 ){
					alert(resultMsg);
				}
				location.replace('%s');
			</script>
			""", resultMsg);
	}
}