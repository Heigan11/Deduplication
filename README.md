# Deduplication

Этот проект решает проблему дублицкации клиентов в основной БД и влиянеие этого события на связанные сервисы.
Технологии:
 - React
 - Spring Boot
 - Rest Api
 - Hibernate
 - MySQL
 - Postgres
 - Kafka

Алгоритм:
1) Через форму по заданному URL вводятся данные клиента
2) Spring Boot приложение (AppendClientApi) получает через Rest Api POST запрос по данному URL.
3) AppendClientApi используя Hibernate создает Entity клиента.
4) AppendClientApi обращается к базе MySQL для поиска клиента по полученным данным.
5) Если клиент не найден, клиенту присвивается уникальный id и добавляется строка в БД. Событие обработано.
6) Если клиент найден, клиент из БД помечается к удалению, заносится новая запись с новым id. 
7) AppendClientApi подключается к топику Kafka и передает сообщение с ключом "deduplication" и сообщением со String представлением JSON клиента.
8) Spring Boot приложение (Consumer) слушает топик Kafka. При получении сообщения с ключом "deduplication" делает POST-запрос к UpdateClientApi.
9) UpdateClientApi используя Hibernate создает Entity клиента.
10) UpdateClientApi обращается к базе Postgres для поиска клиента по старому id.
11) Если клиент не найден, добавляется строка в БД. Событие обработано.
12) Если клиент найден, клиент из БД помечается к удалению, заносится новая запись с новым id. Событие обработано.

Для старта - sudo bash StartKafka.sh
