public class ClientConfig {
  public static final String SERVER_URL = "http://CS6650-LoadBalancer-1084061899.us-west-2.elb.amazonaws.com:8080/Assignment2_LoadBalancer/skiers";
  public static final int NUM_THREADS = 100;  // number of threads
  public static final int REQUESTS_PER_THREAD = 2000;  // number of requests per thread
  public static final int TOTAL_REQUESTS = NUM_THREADS * REQUESTS_PER_THREAD; // number of total requests
  public static final int SEASON_ID = 2025;
  public static final int TIMEOUT_SECONDS = 10; // HTTP timeout
  public static final int MAX_RETRIES = 5; // max times of retry allowed
  public static final String CSV_FILE_PATH = "request_data.csv";
}