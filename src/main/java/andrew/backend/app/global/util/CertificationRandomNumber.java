package andrew.backend.app.global.util;

import java.util.Random;

public class CertificationRandomNumber {
    private static final int size = 20;

    // 이메일 난수 만드는 메서드
    public static String random() {
        Random ran = new Random();
        StringBuilder buf = new StringBuilder();
        int num;
        do {
            num = ran.nextInt(75) + 48;
            if (num <= 57 || num >= 65 && num <= 90 || num >= 97) {
                buf.append((char) num);// 숫자, 영문자만
            }
        } while (buf.length() < size); // 20글자

        return buf.toString();
    }

    public static String randomNumber(){
        Random random = new Random(); //랜덤 객체 생성(디폴트 시드값 : 현재시간)
        random.setSeed(System.currentTimeMillis());
        return String.valueOf(random.nextInt(999999));
    }
}
