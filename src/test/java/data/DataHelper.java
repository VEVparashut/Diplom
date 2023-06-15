package data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static Faker fakerLangEn = new Faker(new Locale("en"));
    private static Faker fakerLangRu = new Faker(new Locale("ru"));

    public static CardInfo getCardNumberForStatusApproved() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getCardNumberForStatusDeclined() {
        return new CardInfo("4444 4444 4444 4442", getValidMonth(), getCurrentYear(), getValidHolder(), getValidCVV());
    }

    /* Тестирование пустой формы*/
    public static CardInfo getFormFromEmptyFields() {
        return new CardInfo("", "", "", "", "");
    }

    /* Тестирование поля "Номер карты"*/
    public static CardInfo getCardNumberForEmptyField() {
        return new CardInfo("", getValidMonth(), getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getCardNumberOf14Digits() {
        return new CardInfo("4444 4444 4444 44", getValidMonth(), getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getCardNumberOfZero() {
        return new CardInfo("0000 0000 0000 0000", getValidMonth(), getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getNonStatusCardNumber() {
        return new CardInfo(getRandomCardNumber(), getValidMonth(), getCurrentYear(), getValidHolder(), getValidCVV());
    }

    /* Тестирование поля "Месяц"*/
    public static CardInfo getTheFirstMonth() {
        return new CardInfo("4444 4444 4444 4441", "01", getAlwaysValidYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getTheLastMonth() {
        return new CardInfo("4444 4444 4444 4441", "12", getAlwaysValidYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getMonthOfZero() {
        return new CardInfo("4444 4444 4444 4441", "00", getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getMonthEmptyField() {
        return new CardInfo("4444 4444 4444 4441", "", getCurrentYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getMonthNotValid() {
        return new CardInfo("4444 4444 4444 4441", "13", getCurrentYear(), getValidHolder(), getValidCVV());
    }

    /* Тестирование поля "Год"*/
    public static CardInfo getYearEmptyField() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), "", getValidHolder(), getValidCVV());
    }

    public static CardInfo getYearOfZero() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), "00", getValidHolder(), getValidCVV());
    }

    public static CardInfo getThePastValueInTheYearField() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getPastYear(), getValidHolder(), getValidCVV());
    }

    public static CardInfo getTheFutureValueInTheYearField() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getFutureYear(), getValidHolder(), getValidCVV());
    }

    /* Тестирование поля "Владелец"*/
    public static CardInfo getHolderEmptyField() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getCurrentYear(), "", getValidCVV());
    }

    public static CardInfo getHolderInCyrillic() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getCurrentYear(), getHolderRusLang(), getValidCVV());
    }

    public static CardInfo getHolderFromDigits() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getCurrentYear(), "123 4567", getValidCVV());
    }

    public static CardInfo getHolderFromSpecialCharacters() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getCurrentYear(), ":;( $%*?;", getValidCVV());
    }

    /* Тестирование поля "CVC/CVV"*/
    public static CardInfo getCVCEmptyField() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getCurrentYear(), getValidHolder(), "");
    }

    public static CardInfo getCVCTwoDigits() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getCurrentYear(), getValidHolder(), "01");
    }

    public static CardInfo getCVCOfZero() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getCurrentYear(), getValidHolder(), "000");
    }

    public static String getValidMonth() {
        String validMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        return validMonth;
    }

    public static String getPastYear() {
        String pastYear = LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        return pastYear;
    }

    public static String getCurrentYear() {
        String currentYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        return currentYear;
    }

    public static String getAlwaysValidYear() {
        String alwaysValidYear = LocalDate.now().plusYears(2).format(DateTimeFormatter.ofPattern("yy"));
        return alwaysValidYear;
    }

    public static String getFutureYear() {
        String futureYear = LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
        return futureYear;
    }

    public static String getValidHolder() {
        return fakerLangEn.name().firstName().toUpperCase() + " " + fakerLangEn.name().lastName().toUpperCase();
    }

    public static String getHolderRusLang() {
        String name = fakerLangRu.name().fullName();
        return name;
    }

    public static String getRandomCardNumber() {
        return fakerLangEn.business().creditCardNumber();
    }

    public static String getValidCVV() {
        return fakerLangEn.number().digits(3);
    }

    @Data
    @AllArgsConstructor
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String holder;
        private String cvc;
    }
}