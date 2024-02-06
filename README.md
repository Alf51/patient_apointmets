Скрип для создание записей в БД расположен по пути: 
```bash
\scripts\postgreSQL\init.sql
```

SOAP запрос:

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gs="http://schedule.ru/">
   <soapenv:Header/>
   <soapenv:Body>
      <gs:getSchedule>
         <gs:startTime>2024-02-05T22:36:17</gs:startTime>
         <gs:durationInMin>60</gs:durationInMin>
         <gs:numberOfTickets>10</gs:numberOfTickets>
         <gs:doctorId>1</gs:doctorId>
      </gs:getSchedule>
   </soapenv:Body>
</soapenv:Envelope>
