package com.kaansonmezoz.shapeshifter.exceptions;

public enum ErrorType {
    //TODO: Daha da iyi bir secenek lazim aslinda cunku hangi parametleri gonderecegini
    //TODO: bilmesi lazim gonderecek olan kisinin. En azindan if else'lerden kurtulduk mesajı uretirken
    //TODO: Ayrıca kod'ta buraları kullanan kisinin runtime'mı yoksa compile time exceptionı kullandigini biliyor olmasi gerekiyor
    //TODO: Bizim genel amacımız custom exceptionlar icin abstract bir layer yaratmak ve bu sayede sadece exception throw ederken hic bu exceptionu throw eden kisinin
    //TODO: Runtime'da (unchecked) mı yoksa compile time'da (checked) mi olan bir exception olduguyla hic ilgilenmemesi gerekiyor.
    //TODO: Aynı sekilde exception'ın mesajlarıyla da ilgilenmemesi gerekiyor. Ve arguman olarak alacagi parametreleri de daha acik bir sekilde almamiz gerekiyor
    //TODO: Mesela NoSuchFieldInTargetObject icin String ... args yerin String sourceField, String targetClass, String errorMessage
    //TODO: Tarzinda alan exception'da bastirilacak olan mesajları alan bir yer alsa cok daha guzel olur en  azından kullanacak olan kisi
    //TODO: Kac tane parametre gonderecegini o parametrelerin sırasini ve o parametlerin anlamini bilmis olur cok daha rahat bir kullanilir.
    //TODO: Ozetle nasıl exception throw etme isini abstract bir hale getirebiliriz buna bakmamiz lazim !
    //TODO: Suanda if-else'ten kurtulduk ki amacimiz aslinda bir bakima buydu dolayısıyla bu sekilde devam etmemiz lazim

    //TODO: ErrorType'ları ikiye ayirabiliriz belki de unchecked ve checked olmaz uzere



    // TODO: Bunu daha da güzel yazabiliriz. Bir exception'ı nasıl throw etmek istiyoruz buna karar vermek lazim
    // TODO: asagidaki gibi bir implementasyonumuz olunca sanki 3 tane farklı bir template'imiz varmis gibi bir durum ortaya cikiyor
    // TODO: Halbuki bizim bir tane metodumuz olmalı exception fırlatan asagidaki kullanım dogru degil aslında daha da guzellestirmek gerekiyor
    // TODO: how to read stack trace tarzı bir sey de diyebiliriz belki de ... yani exception mesajımız altına en sonunda
    // runtime exceptionlarda stacktrace otomatik olarak yaziliyor mu buna bakmak lazim. eger yazilmiyorsa bizim kendimiz altına eklememiz lazim
    // ayni sey checked exception icin de gecerli. ayrıca error tiplerine gore runtime mı checked exception mu atacağımızı hala çözmedik !

    NO_SUCH_FIELD_IN_TARGET_OBJECT(
        "No such field %s found in target class %s\n" +
        "Error message: %s"
    ),

    ILLEGAL_ACCESS_TO_PRIVATE_FIELD(
        "Illegal access to private field %s.\n" +
         "Exception message:\n %s"
    ),

    CANT_CREATE_TARGET_OBJECT(
        "Exception thrown during the initiation of %s.\n" +
        "This might be cause when you don't have no arguments (default) constructor in the target class !\n" +
        "Make sure you have no argument constructor !" +
        "Exception message:\n %s"
    );

    private String errorMessageTemplate;

    ErrorType(String errorMessageTemplate){
        this.errorMessageTemplate = errorMessageTemplate;
    }

    //TODO: Simdilik thrower'ların olmadigi bir dunya olarak bu sekilde bir seyler onerebilirim
    //TODO: Daha iyi bir alternatif bulunabilir elbette

    public void throwRuntimeException(String ... exceptionMessageArguments){
        throw new ShapeShifterRuntimeException(
                errorMessageTemplate,
                exceptionMessageArguments
        );
    }

    public void throwException(String ... exceptionMessageArguments) throws ShapeShifterException {
        throw new ShapeShifterException(
                errorMessageTemplate,
                exceptionMessageArguments
        );
    }
}
