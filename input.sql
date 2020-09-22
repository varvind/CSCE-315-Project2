SELECT attributes_id, business_id, name FROM business WHERE city="Houston";
SELECT attributes_id, business_id, name FROM business WHERE stars > 4.8 AND review_count > 500;
FROM review as r /*left table*/
INNER JOIN tips as t /*list reviews and photos from the same business */
ON r.business_id = t.business_id;
INNER JOIN business as b /*list reviews and  from the same business */
ON b.name,r.text FROM INNER JOIN = b.business_id = r.business_id;
SELECT review_id, user_id, business_id, text FROM review WHERE useful > 27 AND funny > 15; /*high quality yelp reviews */
FROM r /*users and reviews*/
INNER JOIN users as u 
ON r.user_id = u.user_id
SELECT business_id, name, attributes_id FROM attr WHERE takeout="true" AND open24hours = "true";
SELECT business_id, name, attributes_id FROM attr WHERE dietaryrestrictions_kosher="true" AND dietaryrestrictions_halal = "true" AND goodforkids = "true";
SELECT business_id, name, attributes_id FROM attr WHERE "state"="TX" AND goodforkids= "true" AND restaurantstableservice = "true" AND music_background_music = "true";










