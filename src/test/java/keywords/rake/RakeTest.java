package keywords.rake;

import keywords.rake.utils.RakeUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RakeTest {

    private static String testMessages = "Здравствуйте!) Меня зовут Вероника, я менеджер по работе с блогерами \uD83D\uDE0A \n" +
            "Ваш блог очень интересный и с активной аудиторией. Предлагаю сотрудничество с сервисом аудиокниг по подписке) \n" +
            "Готовы рассмотреть наше предложение?) \n" +
            "Давайте немного о проекте и условиях) \n" +
            "Storytel - это сервис аудиокниг по подписке. В библиотеке Storytel собраны аудиокниги практически всех жанров, от классики и нон-фикшена до лекций, стендапов и подкастов. Это сервис, который решает проблему чтения \uD83E\uDD13 \n" +
            "Что нужно: \n" +
            "Опубликовать пост и вспомогательные сторис с призывом перейти по ссылке в шапке профиля. \n" +
            "Что получают Ваши подписчики: \n" +
            "Регистрация по Вашей персональной ссылке даёт бесплатные 30 дней пользования сервисом\uD83D\uDD25 \n" +
            "Что получаете вы: \n" +
            "110 рублей за каждую регистрацию \uD83D\uDE2F\uD83D\uDD25 \n" +
            "Бриф для ознакомления⬇ \n" +
            "https://docs.google.com/document/d/17cdmvwLySlYil7z9t.. Вам интересно такое сотрудничество?) \n" +
            "Наверняка, слышали об этом сервисе?)";

    @Test
    public void sentenceSplitTest() {
        String enText = "Born in Maida Vale, London, Turing was raised in southern England. He graduated at King's College, Cambridge with a degree in mathematics. Whilst he was a fellow at Cambridge, he published a proof demonstrating that some purely mathematical yes–no questions can never be answered by computation and defined a Turing machine, and went on to prove the halting problem for Turing machines is undecidable.";
        enText = enText.toLowerCase();


        List<String> sentences = RakeUtils.splitSentences(enText);
        System.out.println(sentences);
    }

    @Test
    public void applyTest() throws Exception {
        Rake rake = new Rake(RakeUtils.RUSSIAN);
        System.out.println(rake.apply(testMessages));
    }
}
