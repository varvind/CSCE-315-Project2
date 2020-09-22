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
ON r.user_id = u.user_id;
SELECT business_id, name, attributes_id FROM attr WHERE takeout="true" AND open24hours = "true";

SELECT business_id, name, attributes_id FROM attr WHERE "state"="TX" AND goodforkids= "true" AND restaurantstableservice = "true" AND music_background_music = "true";

select business.business_id, business.name from business, attr where business.is_open=1 and attr.happyhour = "true" and business.business_id = attr.business_id; /*business is open and happy hour attribute */
select user_id, name from users where users.fans > 20; /*users with more than 20 fans*/
select review.review_id, review.business_id, review.text from review, business where  review.business_id = business.business_id and review.stars > 4 and business.is_open = 1; /*reviews for businesses with 4 stars that are open*/
select attr.business_id, attr.name, checkin.text from attr, checkin where attr.business_id = checkin.business_id and attr.ambience_intimate = "true"; /*checkins at intimate ambience restaurants*/
select attr.business_id, attr.name, review.text, review.user_id from review, attr where attr.business_id = review.business_id and restaurantsdelivery = "true" and goodformeal_breakfast = "true"; /*breakfast delivery restaurant*/
SELECT users.user_id, users.name, review.text, tip.text FROM users, tip, review WHERE users.user_id = tip.user_id and tip.user_id = review.user_id; /*reviews and tips from same user*/
SELECT business_id, name, attributes_id FROM attr WHERE dietaryrestrictions_kosher="true" AND dietaryrestrictions_halal = "true" AND goodforkids = "true";
select review.review_id, attr.business_id, business.name, review.text, users.name from review, business, users, attr where  review.user_id = users.user_id and business.business_id = review.business_id  and attr.music_jukebox = "true" and business.is_open = 1; /*reviews for businesses with jukebox music that are open*/
select business.business_id, business.name from business, attr where business.is_open=1 and attr.businessparking_garage = "true" attr.goodforkids = "true" and business.business_id = attr.business_id;
SELECT business_id, name, attributes_id FROM attr WHERE dietaryrestrictions_dairy_free="true" AND dietaryrestrictions_gluten_free = "true";
select review.review_id, attr.business_id, review.text, review.user_id, business.name from review, business, attr where  review.business_id = business.business_id and attr.business_id = business.business_id and review.stars < 3 and business.is_open = 0; /*reviews for businesses with low reviews and not open*/
select review.review_id, attr.business_id, review.text, review.user_id, business.name from review, business, attr where  review.business_id = business.business_id and attr.business_id = business.business_id and review.stars >3 and attr.hairspecializesin_perms = "true"; /*good hair perm places */
select attr.business_id, tip.text, business.name, tip.user_id from tip, business, attr where  tip.business_id = business.business_id and attr.business_id = business.business_id and review.stars > 3 and attr.goodformeal_dessert = "true"; /*tips for dessert places */

















