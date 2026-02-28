package sayitahminoyunu;
import java.util.Scanner;
//programın başlangıcı ve konsol etkileşimlerini kontrol eder
//statik main metodu içerir
public class App {
    //programın başlangıç noktası
    public static void main(String[] args) {
        //scanner nesnesi ekrandan girdi almak için
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Number Guess Game ===\n");
        //1. AŞAMA: Yapılandırma Ayarları
        //aralık seçimi
        System.out.println("Range? (1) 1-100  (2) 1-1000 : ");
        int rangeChoice = safeReadInt(scanner, 1, 2);
        
        int min = 1;
        int max = (rangeChoice == 1) ? 100 : 1000;
        
        //deneme hakkı
        int defaultAttempts = (rangeChoice == 1) ? 7 : 10;
        System.out.println("Max attempts (default " + defaultAttempts + ")? : ");
        int maxAttempts = safeReadInt(scanner, 1, 100);
        if (maxAttempts == 0) maxAttempts = defaultAttempts;//enter basarsa default
        //süre kısıtı
        System.out.println("Use time limit? (y/n): ");
        String timeLimitChoice = scanner.nextLine().trim().toLowerCase();
        int timeLimitSeconds = 0;
        if (timeLimitChoice.equals("y") || timeLimitChoice.equals("yes")) {
            System.out.println("Total seconds (default 60)? : ");
            timeLimitSeconds = safeReadInt(scanner, 1, 600);
            if (timeLimitSeconds == 0) timeLimitSeconds = 60;
        }
        //GameConfig oluşturur
        GameConfig config = new GameConfig(min, max, maxAttempts, timeLimitSeconds);
        //2. AŞAMA: Oyunu Başlat
        NumberGuessGame game = new NumberGuessGame(config);       
        System.out.println("\nGame started! Guess the number between " + min + " and " + max + ".\n");  
        //3. AŞAMA: Oyun Döngüsü
        while (!game.isOver()) {
            //kullanıcıya bilgi gösterir
            System.out.println(game.prompt());
            System.out.print("Your guess: ");
            //güvenli sayı okuma
            int guess = safeReadInt(scanner, min, max);
            if (guess == -1) continue;//hatalı giriş, tekrar giriş istemi
            //tahmini değerlendirir
            Attempt attempt = game.evaluateGuess(guess);
            //sonucu gösterir
            if (attempt.getResult() == Attempt.Result.CORRECT) {
                System.out.println("🎉 Correct! You won in " + attempt.getAttemptNo() + " attempts.\n");
            } else if (attempt.getResult() == Attempt.Result.HIGH) {
                System.out.println("⬇️  Too High!\n");
            } else {
                System.out.println("⬆️  Too Low!\n");
            }
        }
        //4. AŞAMA: Oyun Sonu
        System.out.println("========== GAME OVER ==========");
        if (game.isWon()) {
            System.out.println("Congratulations! You found the number: " + game.getSecret());
        } else {
            //süre mi bitti, deneme mi?
            if (config.isTimeLimited() && game.getRemainingSeconds() <= 0) {
                System.out.println("Time is up! The number was: " + game.getSecret());
            } else {
                System.out.println("Out of attempts! The number was: " + game.getSecret());
            }
        }
        //özet istatistikler
        System.out.println("\n--- Attempt Summary ---");
        for (Attempt att : game.getAttempts()) {
            System.out.println(att);
        }
        System.out.println("\nThanks for playing!");
        scanner.close();//kaynak sızıntısını önleriz
    }
    private static int safeReadInt(Scanner scanner, int min, int max) {
        try {
            String input = scanner.nextLine().trim();
            //boş girişi 0 olarak döndürür
            if (input.isEmpty()) return 0;
            int value = Integer.parseInt(input);
            //aralık kontrolü
            if (value < min || value > max) {
                System.out.println("Please enter a number between " + min + " and " + max + ".");
                return -1;
            }
            return value;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
            return -1;
        }
    }
}