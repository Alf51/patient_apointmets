### Для запуска необходимо:

1.Java 17

2.База данных PostgeSQL


## Инструкция по запуску

**1. Скачайте этот репозиторий к себе на компьютер**

```bash
git clone https://github.com/Alf51/patient_apointmets.git
```

**2. В application.propertiesn установите праметры своей PosgreSQL БД**

**3. Запустите задачу генерации доменных классов на основе WSDL**
Перейдите в корень проекта и выполните команду.

Для windows: 
```bash
mvn generate-sources
```

Пример в программе IDEA pom.xml -> right click -> Maven -> Generate Sources and Update Folders:

![image](/images/wdsl.png)


java -jar .\patient_apointmets-0.0.1-SNAPSHOT.jar

**4. Запустите приложение через консоль**
В корене проекта выполните команду:

Пример на windows:

```bash
.\mvnw.cmd package
```

Появиться папка <b>target</b> и нужно перейти в неё  ---> ``` cd target ```

Теперь запускаем наш скомпилированный файл, с расширением  <b>.jar</b>  ----> ```java -jar .\patient_apointmets-0.0.1-SNAPSHOT.jar```

**5. Коллекция запросов для тестирования приложения postman расположена по адресу**

```bash
\request\ticket.postman_collection.json
```
