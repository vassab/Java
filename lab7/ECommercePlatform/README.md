Електронна Комерція (E-Commerce) Бек-Енд
Опис
Цей проект реалізує спрощений бек-енд для платформи електронної комерції, використовуючи Java Collections. Основний фокус - управління запасами, корзиною користувача та обробка замовлень.

# Класи

## Product (Товар)
Поля: id (Integer), name (String), price (double), stock (int) <br />
Конструктори, гетери та сетери. <br />
Перевизначення методу toString для правильного представлення.

## User (Користувач)

Поля: id (Integer), username (String), cart (Map<Product, Integer>)
Конструктори, гетери, сетери та методи для додавання, видалення та модифікації товарів у кошику.

## Order (Замовлення)
Поля: id (Integer), userId (Integer), orderDetails (Map<Product, Integer>), totalPrice (double) <br />
Конструктори, гетери, сетери та метод для розрахунку загальної вартості замовлення.<br />
ECommercePlatform (Платформа Електронної Комерції) <br />
Поля: users (Map<Integer, User>), products (Map<Integer, Product>), orders (Map<Integer, Order>)
Методи для додавання користувачів, товарів, створення замовлення, переліку доступних товарів, користувачів та замовлень та оновлення запасів товарів.


## Рекомендації для Користувача
Функція для рекомендації товарів користувачам на основі товарів у їхньому кошику чи історії замовлень.
<br />
Клас ECommerceDemo буде використовуватися для ініціалізації платформи, додавання користувачів та товарів, симуляції взаємодії користувачів із кошиком та створення та обробки замовлень.