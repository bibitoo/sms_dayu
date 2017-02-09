package cc.landking.web.sms_dayu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SmsAliDayuService service = new SmsAliDayuService();
        service.setAppKey("23669955");
        service.setAppSecret("320396eba697264367f1c602cd735b98");
//        service.sendRegisterCode("12356", "18988772132");
        service.sendResetPasswordCode("1256", "18988772132");
    }
}
