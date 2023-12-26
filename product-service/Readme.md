swagger-site: http://localhost:8080/swagger-ui/index.html#/

db: mongodb+srv://admin:admin@cluster0.7sopmmd.mongodb.net/microservice-db

api example:

create product:

{
"product": {
"name": "Áo Thun Levents Mini Popular Logo/ Black",
"industrialId": "1703564448833216",
"industrialTypeName": "Thời trang",
"description": "test",
"featuredImageUrl": "https://down-vn.img.susercontent.com/file/9f83851fce5943b9e3ba8788425bc82e",
"imageUrls": [
"https://down-vn.img.susercontent.com/file/fe422009a6dce39bd4673ebd4a439257","https://down-vn.img.susercontent.com/file/75795fe2b232b2a5e42c11de5467f96f","https://down-vn.img.susercontent.com/file/899f683d15460eea4c771b10caaf68d6","https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lndws3ijfxm5db"
],
"title": "Áo Thun Levents Mini Popular Logo/ Black",
"discount": 7,
"tradeMarkId": "1703564782778478"
},
"productVariants": [
{
"imageUrl": "https://down-vn.img.susercontent.com/file/8fbb7f355f507d76012c1bb8968246fb",
"price": {
"amount": 350000,
"currencyCode": "VND"
},
"weight": 100,
"weightUnit": "GRAMS",
"dimension": {
"dimensionUnit": "MM",
"width": 100,
"height": 100,
"length": 100
},
"quantityAvailable": 20,
"requiresShipping": true,
"title": "Áo Thun Levents XL Logo Black/ White",
"color": "BLACK",
"size": "S",
"discount": 5
},
{
"imageUrl": "https://down-vn.img.susercontent.com/file/75795fe2b232b2a5e42c11de5467f96f",
"price": {
"amount": 340000,
"currencyCode": "VND"
},
"weight": 100,
"weightUnit": "GRAMS",
"dimension": {
"dimensionUnit": "MM",
"width": 100,
"height": 100,
"length": 100
},
"quantityAvailable": 20,
"requiresShipping": true,
"title": "Áo Thun Levents XL Logo Black/ White",
"color": "BLACK",
"size": "L",
"discount": 5
},
{
"imageUrl": "https://down-vn.img.susercontent.com/file/912fd76e4d049aaec9a0d9431c548894",
"price": {
"amount": 370000,
"currencyCode": "VND"
},
"weight": 100,
"weightUnit": "GRAMS",
"dimension": {
"dimensionUnit": "MM",
"width": 100,
"height": 100,
"length": 100
},
"quantityAvailable": 20,
"requiresShipping": true,
"title": "Áo Thun Levents XL Logo Black/ White",
"color": "BLACK",
"size": "M",
"discount": 5
}
]
}