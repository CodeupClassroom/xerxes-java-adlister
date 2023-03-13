public class DaoFactory {
    private static Ads adsDao;
    private static Books booksDao;

    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new ListAdsDao();
        }
        return adsDao;
    }

    public static Books getBooksDao() {
        if (booksDao == null) {
            Config config = new Config();
            booksDao = new MySQLBooksDao(config);
        }
        return booksDao;
    }
}
