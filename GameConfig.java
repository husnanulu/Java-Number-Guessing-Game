package sayitahminoyunu;
//oyun ayarlarını tutmak için sınıf
//dinamik bir sınıftır her oyun için yeni nesne oluşturur
class GameConfig {
    //alanları private yapıyoruz, dışarıdan direkt erişilmesin
    private int min;//tahmin edilebilecek minimum değeri
    private int max;//tahmin edilebilecek maximum değeri
    private int maxAttempts;//maksimum deneme hakkı
    private int timeLimitSeconds;//süre kısıtı
    //constructor: GameConfig nesnesi oluşturulurken çağrılır
    public GameConfig(int min, int max, int maxAttempts, int timeLimitSeconds) {
        this.min = min;
        this.max = max;
        this.maxAttempts = maxAttempts;
        this.timeLimitSeconds = timeLimitSeconds;
    }
    //getter metotları: private alanlara dışarıdan erişim sağlar
    public int getMin() { return min; }
    public int getMax() { return max; }
    public int getMaxAttempts() { return maxAttempts; }
    public int getTimeLimitSeconds() { return timeLimitSeconds; }
    //süre kısıtı olup olmadığını kontrol eder
    public boolean isTimeLimited() {
        return timeLimitSeconds > 0;
    }
}