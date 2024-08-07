## 1. Перечень автоматизируемых сценариев.

Купить (Оплата по карте) вводим во все поля валидные значения
1. [Заходим на сайт](http://localhost:8080/)
2. Кликаем по иконке "Купить"
3. Заполняем поля "Номер карты" (16 цифр), "Месяц"(2 цифры), "Год"(2 последние цифры года), "Владелец"(латиницей), "CVC/CVV"(3 цифры)
4. Кликаем иконку "Продолжить"
Ожидаемый результат: 
- Успешное выполнение операции
- Списание с карты 45 000 руб
- Поступление 33 360 миль на карту


Купить в кредит (Кредит по данным карты) вводим во все поля валидные значения
1. [Заходим на сайт](http://localhost:8080/)
2. Кликаем по иконке "Купить в кредит"
3. Заполняем поля "Номер карты", "Месяц", "Год", "Владелец", "CVC/CVV"
4. Кликаем иконку "Продолжить"
Ожидаемый результат: 
- Успешное выполнение операции/поступление на карту 45 000 руб
- Покупка тура кредитными средствами

### Перечень сценариев с невалидными данными 
Купить (Оплата по карте) дебетовая карта 

#### Проверка невалидных данных номера карты
1. [Заходим на сайт](http://localhost:8080/) 
2. Выбираем раздел "Купить"
3. Заполняем поле "Номер карты"
- Проверка граничных значений (15 и 17)
- Пропуск поля (оставить без заполнения)
- Ввести буквы
- Ввести символы
4. Все оставшиеся поля заполняем валидными данными
5. Кликаем иконку "Продолжить"
Ожидаемый результат:
Поле "Номер карты" заполнено неверно

#### Проверка невалидных данных месяца
1. [Заходим на сайт](http://localhost:8080/)
2. Выбираем раздел "Купить"
3. Заполняем поле "Месяц"
- Ввод 1/3 цифр
- Пропуск поля (оставить без заполнения)
- Ввести буквы
- Ввести символы
4. Все оставшиеся поля заполняем валидными данными
5. Кликаем иконку "Продолжить"
   Ожидаемый результат:
   Поле "Месяц" заполнен неверно

#### Проверка невалидных данных года
1. [Заходим на сайт](http://localhost:8080/)
2. Выбираем раздел "Купить"
3. Заполняем поле "Год"
- Ввод 1/3 цифр
- Ввод прошедшего года (пр. 23)
- Ввод нынешнего года с прошедшим месяцем (пр. 01)
- Пропуск поля (оставить без заполнения)
- Ввести буквы
- Ввести символы
4. Все оставшиеся поля заполняем валидными данными
5. Кликаем иконку "Продолжить"
   Ожидаемый результат:
   Текст "Срок карты истёк" 

#### Проверка невалидных данных "Владелец"
1. [Заходим на сайт](http://localhost:8080/)
2. Выбираем раздел "Купить"
3. Заполняем поле "Владелец"
- Ввод цифр
- Ввод 1 буквы
- Ввод на кириллице
- Пропуск поля (оставить без заполнения)
- Ввести буквы
- Ввести символы
4. Все оставшиеся поля заполняем валидными данными
5. Кликаем иконку "Продолжить"
   Ожидаемый результат:
   Имя владельца карты указано неверно

#### Проверка невалидных данных "CVC/CVV"
1. [Заходим на сайт](http://localhost:8080/)
2. Выбираем раздел "Купить"
3. Заполняем поле "CVC/CVV"
- Ввод граничных значений (2/4 цифр)
- Пропуск поля (оставить без заполнения)
- Ввести буквы
- Ввести символы
4. Все оставшиеся поля заполняем валидными данными
5. Кликаем иконку "Продолжить"
   Ожидаемый результат:
   Блокировка транзакции

Кредит по данным карты (Кредитная карта)

#### Проверка невалидных данных номера карты
1. [Заходим на сайт](http://localhost:8080/)
2. Выбираем раздел "Купить в кредит"
3. Заполняем поле "Номер карты"
- Проверка граничных значений (15 и 17)
- Пропуск поля (оставить без заполнения)
- Ввести буквы
- Ввести символы
4. Все оставшиеся поля заполняем валидными данными
5. Кликаем иконку "Продолжить"
   Ожидаемый результат:
   Поле "Номер карты" заполнено неверно

#### Проверка невалидных данных месяца
1. [Заходим на сайт](http://localhost:8080/)
2. Выбираем раздел "Купить в кредит"
3. Заполняем поле "Месяц"
- Ввод 1/3 цифр
- Пропуск поля (оставить без заполнения)
- Ввести буквы
- Ввести символы
4. Все оставшиеся поля заполняем валидными данными
5. Кликаем иконку "Продолжить"
   Ожидаемый результат:
   Поле "Месяц" заполнен неверно

#### Проверка невалидных данных года
1. [Заходим на сайт](http://localhost:8080/)
2. Выбираем раздел "Купить в кредит"
3. Заполняем поле "Год"
- Ввод 1/3 цифр
- Ввод прошедшего года (пр. 23)
- Ввод нынешнего года с прошедшим месяцем (пр. 01)
- Пропуск поля (оставить без заполнения)
- Ввести буквы
- Ввести символы
4. Все оставшиеся поля заполняем валидными данными
5. Кликаем иконку "Продолжить"
   Ожидаемый результат:
   Текст "Срок карты истёк"

#### Проверка невалидных данных "Владелец"
1. [Заходим на сайт](http://localhost:8080/)
2. Выбираем раздел "Купить в кредит"
3. Заполняем поле "Владелец"
- Ввод цифр
- Ввод 1 буквы
- Ввод на кириллице
- Пропуск поля (оставить без заполнения)
- Ввести буквы
- Ввести символы
4. Все оставшиеся поля заполняем валидными данными
5. Кликаем иконку "Продолжить"
   Ожидаемый результат:
   Имя владельца карты указано неверно

#### Проверка невалидных данных "CVC/CVV"
1. [Заходим на сайт](http://localhost:8080/)
2. Выбираем раздел "Купить в кредит"
3. Заполняем поле "CVC/CVV"
- Ввод граничных значений (2/4 цифр)
- Пропуск поля (оставить без заполнения)
- Ввести буквы
- Ввести символы
4. Все оставшиеся поля заполняем валидными данными
5. Кликаем иконку "Продолжить"
   Ожидаемый результат:
   Блокировка транзакции

## 2. Перечень используемых инструментов с обоснованием выбора.
- IDEA (привычный интерфейс)
- Java (язык тесторования)
- Junit5 (написание тестов)
- Selenide (простой в написании автотестов)
- DevTools (панель изучения сайта + тестовые метки)
- Docker (создание контейнеров)
- Faker (для написания инвалидных тестов)
- Allure (наглядная таблица тестов, которую можно предоставить начальству)

## 3. Перечень и описание возможных рисков при автоматизации.
- Лишние тесты
- Технические проблемы
- Неверный подбор инструментов
- Отсутствие тестовых меток
- Трудности с поиском локаторов элементов

## 4. Интервальная оценка с учётом рисков в часах.
25-30 часов (+25% закладываем на риски)

## 5. План сдачи работ: когда будут готовы автотесты, результаты их прогона.
7-8 рабочих дней на написание всех требуемых автотестов
1-2 дня на прогон всех тестов и заполнение отчётности + выявление багов и написание issues