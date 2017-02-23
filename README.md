
# Программа для чтения json структур. 
После запуска нужно добавить новую JSON структуру, в которой обязательно должен присутствовать ключ description и массив для записей properties.

Дальше нужно нажать "Просмотреть записи" и по заполненной таблице добавить запись.

Пример структуры:
```sh
{
"title": "Автомобили",
"type": "object",
"properties": {
  "brand": {
    "type": "string",
    "description": "Марка"
  },
  "model": {
    "type": "string",
    "description": "Модель"
  },
  "price": {
    "type": "number",
    "minimum": 0,
    "description": "Стоимость"
  },
  "currency": {
    "type": "array",
    "default": [
      "Рубли",
      "Доллары",
      "Тенге"
    ],
    "description": "Валюта"
  }
},
"required": [
  "brand",
  "model"
]
}
```

Пример записи:
```sh
{
"brand": "Toyota",
"model": "Land Cruiser 200",
"price": 5000000.00,
"currency": "Рубли"
}
```