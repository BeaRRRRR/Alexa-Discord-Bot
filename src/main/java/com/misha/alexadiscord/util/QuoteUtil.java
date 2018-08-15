package com.misha.alexadiscord.util;

import java.util.Random;

public class QuoteUtil {

    static String[] enQuotes = {
            "Start by doing what's necessary; then do what's possible; and suddenly you are doing the impossible.",
            "Believe you can and you're halfway there.",
            "It does not matter how slowly you go as long as you do not stop.",
            "Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time.",
            "The will to win, the desire to succeed, the urge to reach your full potential... these are the keys that will unlock the door to personal excellence.",
            "Don't watch the clock; do what it does. Keep going.",
            "A creative man is motivated by the desire to achieve, not by the desire to beat others.",
            "A creative man is motivated by the desire to achieve, not by the desire to beat others.",
            "Expect problems and eat them for breakfast.",
            "Start where you are. Use what you have. Do what you can.",
            "Ever tried. Ever failed. No matter. Try Again. Fail again. Fail better.",
            "Be yourself; everyone else is already taken.",
            "Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.",
            "Always remember that you are absolutely unique. Just like everyone else.",
            "Do not take life too seriously. You will never get out of it alive.",
            "People who think they know everything are a great annoyance to those of us who do.",
            "Procrastination is the art of keeping up with yesterday.",
            "Get your facts first, then you can distort them as you please.",
            "A day without sunshine is like, you know, night.",
            "My grandmother started walking five miles a day when she was sixty. She's ninety-seven now, and we don't know where the hell she is.",
            "Don't sweat the petty things and don't pet the sweaty things.",
            "Always do whatever's next.",
            "Atheism is a non-prophet organization.",
            "Hapiness is not something ready made. It comes from your own actions.",
            "If I could drop dead right now, I'd be the happiest man alive.",
            "God give me strength to face a fact though it slay me."
    };

    static String[] ruQuotes = {
            "Выигранное сражение сглаживает все другие промахи, и обратно: поражение делает бесполезными все прежние успехи.",
            "Тот, кто не может лгать, не знает, что такое правда.",
            "Вы можете узнать, чего ваш враг боится больше всего, наблюдая, какие средства он использует, чтобы устрашить вас.",
            "Если вы не учитесь на своих ошибках, нет смысла их делать.",
            "Друзья и совесть бывают у человека до тех пор, пока они не нужны.",
            "Потребность женщины - выйти замуж возможно скорее, потребность мужчины - не жениться, пока хватает сил.",
            "Хотя лень рабского подчинения для абсолютного большинства людей имеет свою прелесть, тот, кто создан из более благородной материи, не приемлет никакие блага мира без свободы.",
            "Опыт помогает женщине выйти замуж, а мужчине остаться холостым.",
            "Я шире распахнул завесу истины, чем кто-либо из смертных до меня. Но я хотел бы видеть того, кто мог бы похвалиться более ничтожными современниками, чем те, среди которых я жил.",
            "Главный урок истории состоит в том, что человечество необучаемо.",
            "Все приходит вовремя, если люди умеют ждать.",
            "Миром правят идеи, пришедшие неслышными шагами.",
            "Чтобы любить друг друга, нужно бороться с собой.",
            "В отличие от Фрейда я не верю, что секс является определяющим фактором в комплексе поведения человека. Мне кажется, холод, голод и позор нищеты гораздо глубже определяют его психологию.",
            "В практической жизни от гения проку не больше, чем от телескопа в театре.",
            "Смеяться можно над чем угодно, но не когда угодно. Мы шутим по поводу смертного ложа, но не у смертного ложа. Жизнь серьезна всегда, но жить всегда серьезно - нельзя.",
            "Есть такие люди, презрение которых делают больше чести, чем их дружба.",
            "Обычно арьергард прежнего авангарда является авангардом нового арьергарда.",
            "Неужели Вы думаете, что, помогая несчастным, покупаете право оскорблять их?",
            "Образование делает хорошего человека лучше, а плохого - хуже.",
            "Жизнь дарит человеку в лучшем случае лишь одно великое мгновение, и секрет счастья в том, чтобы это великое мгновение переживать как можно чаще.",
            "Читать - значит думать чужой головой вместо своей собственной.",
            "Счастлив тот, кто счастлив дома.",
            "Именно в возрасте от шестнадцати до двадцати пяти лет обычно решается наша судьба.",
            "Из всех наиболее эффективных и надежных средств ада, изобретенных дьяволом для разрушения любви, ворчание всегда являлось смертельным. Это средство еще никогда не отказывало. Подобно укусу королевской кобры, оно убивает любовь наповал...",
            "Скука - серьезная проблема для моралиста, ибо со скуки совершается по крайней мере половина всех грехов человечества.",
            "Истина происходит от чувств, но не заключается в них."
    };

    public static String getRandomQuote(String lang){
        if(lang.equals("en")) return enQuotes[new Random().nextInt(enQuotes.length-1)];
        return ruQuotes[new Random().nextInt(ruQuotes.length-1)];
    }

}
