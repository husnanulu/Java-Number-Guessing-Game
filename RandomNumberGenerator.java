package sayitahminoyunu;
import java.util.Random;
//belirtilen aralıkta rastgele sayı üretir
class RandomNumberGenerator {
    private int min;//üretilecek sayının minimum değeri
    private int max;//üretilecek sayının maximum değeri
    private Random random;//java'nın Random sınıfı
    public RandomNumberGenerator(int min, int max) {
        this.min = min;
        this.max = max;
        this.random = new Random();//rastgele sayı üreteci
    }
    public int generate() {
        //nextInt(max - min + 1) (max-min) arasında sayı üretir
        //random() 0.0 ile 1.0 arasında sayı üretir
        //+ min ekleyerek istediğimiz aralığa kaydırırız
        return random.nextInt(max - min + 1) + min;
    }
}