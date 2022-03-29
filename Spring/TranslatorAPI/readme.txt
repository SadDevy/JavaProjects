TranslatorAPI.

Внешний API: https://libretranslate.de/translate
На вход JSON, например:
{
    "q": "Hello!",
    "source": "en",
    "target": "ru"
}

TranslatorAPI: https://customtranslator.herokuapp.com/api/translate
На вход JSON, например:
{
    "language": "en-ru",
    "toTranslate": "Hello!"
}

Выход JSON:
{
    "source": "en",
    "target": "ru",
    "translatedText": "Здравствуйте"
}

Используется in-memory h2 database.
Параметры подключения -> application.properties

Строка парсится по regex -> "[, .?:;!]+"
Пример (вход): "Hello, world! Where is Huston?"
Пример (выход): "Привет мир Где Хьюстон"