package sayitahminoyunu;
import java.util.ArrayList;
import java.util.List;
class NumberGuessGame {
    // Oyun alanları
    private GameConfig config;//oyun ayarları
    private int secretNumber;//gizli sayı
    private int remainingAttempts;//kalan deneme hakkı
    private List<Attempt> attempts;//tahminleri tutan liste
    private Countdown countdown;//geri sayım
    private boolean finished;//oyun bitiş durumu
    private boolean won;//oyunu kazanıp kazanmadığı
    //constructor: oyunu başlatır, gizli sayıyı üretir
    public NumberGuessGame(GameConfig config) {
        this.config = config;
        this.attempts = new ArrayList<>();//boş liste oluşturur
        //rastgele sayı üretir
        RandomNumberGenerator rng = new RandomNumberGenerator(config.getMin(), config.getMax());
        this.secretNumber = rng.generate();
        //deneme hakkını ayarla
        this.remainingAttempts = config.getMaxAttempts();
        //süre kısıtı varsa countdown başlat
        if (config.isTimeLimited()) {
            this.countdown = new Countdown(config.getTimeLimitSeconds());
            this.countdown.start();//geri sayımı başlat
        }
        this.finished = false;
        this.won = false;
    }
    public String prompt() {
        StringBuilder sb = new StringBuilder();
        sb.append("Attempts left: ").append(remainingAttempts);
        //eğer süre kısıtı varsa ekler
        if (config.isTimeLimited()) {
            sb.append(" | Time left: ").append(countdown.remainingSeconds()).append("s");
        }
        return sb.toString();
    }
    public Attempt evaluateGuess(int guess) {
        //kaçıncı deneme olduğunu hesaplar
        int attemptNo = attempts.size() + 1;
        //sonucu belirler
        Attempt.Result result;
        if (guess > secretNumber) {
            result = Attempt.Result.HIGH;
        } else if (guess < secretNumber) {
            result = Attempt.Result.LOW;
        } else {
            result = Attempt.Result.CORRECT;
            this.won = true;//kazandı
            this.finished = true;//oyun bitti
        }
        //attempt nesnesi oluştur ve listeye ekle
        Attempt attempt = new Attempt(guess, result, attemptNo);
        attempts.add(attempt);
        //deneme hakkını azalt
        remainingAttempts--;
        //deneme hakkı bittiyse oyun biter
        if (remainingAttempts <= 0 && !won) {
            this.finished = true;
        }
        return attempt;
    }
    public boolean isOver() {
        //kazandıysa veya finished flag true ise
        if (finished) return true;
        //süre bittiyse
        if (config.isTimeLimited() && countdown.isExpired()) {
            this.finished = true;
            return true;
        }
        return false;
    }
    public boolean isWon() {
        return won;
    }
    public int getSecret() {
        return secretNumber;
    }
    public int getRemainingAttempts() {
        return remainingAttempts;
    }
    public int getRemainingSeconds() {
        if (config.isTimeLimited()) {
            return countdown.remainingSeconds();
        }
        return -1;//süre kısıtı yok
    }
    public List<Attempt> getAttempts() {
        return attempts;
    }
}