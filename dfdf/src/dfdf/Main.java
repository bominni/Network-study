package dfdf;

public class Main
{
    public static void main(String[] args)
    {
        // Body 클래스의 객체 생성
        Body body = new Body();
        
        // 객체를 통해 Body 클래스의 속성에 값을 집어넣는다
        body.arm = "팔";
        body.ear = "귀";
        body.eye = "눈";
        body.leg = "다리";
        System.out.println("arm = " + body.arm + ", ear = " + body.ear + ", eye = " + body.eye +
                ", leg = " + body.leg);
        
        // 객체를 통해 Body 클래스의 메서드를 호출해 작동하도록 지시한다
        body.see();
        body.grab();
        body.listen();
        body.walk();
    }
}