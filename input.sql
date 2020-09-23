--1
SELECT name, city, attribute_id, business_id FROM business 
WHERE city = 'Houston'; 
/*View all businesses in Houston*/


--2
SELECT name, stars, review_count FROM business 
WHERE stars > 4.8 AND review_count > 20 
ORDER BY stars, review_count; 
/*View businesses with at least a 4.8 rating and 20 reviews. Orders them first by rating, then by number of reviews*/


--3
SELECT b.name, t.text FROM tip as t /*left table*/
INNER JOIN business AS b /*list reviews and photos from the same business */
ON t.business_id = b.business_id WHERE b.name = 'Starbucks';
/*View all tips from Starbucks*/


--4 
SELECT b.name as name, r.text AS review FROM review AS r
INNER JOIN business AS b /*list reviews and  from the same business */
ON b.business_id = r.business_id WHERE b.name = 'Starbucks';
/*View all text reviews from Starbucks*/


--5
SELECT u.name as user, b.name as business, r.text FROM review AS r 
INNER JOIN users as u 
ON r.user_id = u.user_id 
INNER JOIN business as b ON b.business_id = r.business_id
WHERE r.useful > 27 AND r.funny > 15 AND b.name = 'Starbucks'; 
/*View "high quality" yelp reviews for Starbucks*/

--6
SELECT b.name FROM business AS b INNER JOIN attr AS a 
on  b.attribute_id = a.attributes_id WHERE restaurantstakeout= 'True' AND open24hours = 'True';
/*View restuarants  that allow takeout and are open 24 hours*/


--7
SELECT b.name FROM business AS b 
INNER JOIN attr AS a on  b.attribute_id = a.attributes_id 
WHERE b.state= 'TX' AND a.goodforkids= 'True';; 
/*All restaurants in TX that are also good for kids*/


--8
SELECT b.name from business AS b
INNER JOIN attr AS a ON b.business_id = a.business_id
WHERE b.is_open = 1 and a.happyhour = 'True';
/*All resturants that are currently open and have happy hour*/


--9
SELECT user_id, name FROM users WHERE users.fans > 20; 
/*users with more than 20 fans*/


--10 (No sytnax error)
SELECT b.name, r.text FROM business as b
INNER JOIN review as r ON r.business_id = b.business_id
WHERE b.stars > 4 and b.is_open = 1; 
/*Reviews for businesses with 4 stars that are currently open*/


--11
SELECT b.name, a.ambience_intimate, c.date from attr as a
INNER JOIN checkin c ON c.business_id = a.business_id
INNER JOIN business b ON b,businss_id = a.business_id
WHERE a.ambience_intimate = 'True'; 
/*All the checkins at restaurants with an intimate ambience*/


--12
SELECT b.name FROM business AS b
INNER JOIN attr AS a ON b.business_id = a.business_id
WHERE a.restaurantsdelivery = 'True' AND goodformeal_breakfast = 'True'; 
/*All the resturants names that serve breakfast and do delivery*/


--13 (No syntax error)
SELECT u.name, b.name AS business, r.text AS review FROM users AS u
INNER JOIN review AS r ON u.user_id = r.user_id
INNER JOIN business AS b ON b.business_id = r.business_id
ORDER BY u.name, b.name; 
/*All reviews for all users ordererd by user and then by business*/


--14
SELECT b.name FROM business AS b
INNER JOIN attr AS a ON b.business_id = a.business_id
WHERE a.dietaryrestrictions_halal = 'True';
/*All the restuarnats who serve halal food*/



--15 (No syntax error)
SELECT b.name, u.name AS user, r.text AS review , a.music_jukebox FROM business AS b
INNER JOIN attr AS a ON a.business_id = b.business_id
INNER JOIN review AS r ON r.business_id = b.business_id
INNER JOIN users AS u ON u.user_id = r.user_id
WHERE a.music_jukebox = 'True';
/*Reviews for all the business that play jukebox music*/


--16
SELECT b.name FROM business
INNER JOIN attr AS a ON b.business_id = a.business_id 
WHERE b.is_open=1 AND a.businessparking_garage = 'True' AND a.goodforkids = 'True';
/*All businesses that are currently open, offer parking garage parking, and are good for kids*/

--17
SELECT b.name FROM business AS b
INNER JOIN attr AS a ON b.business_id = a.business_id 
WHERE a.dietaryrestrictions_dairy_free = 'True' AND dietaryrestrictions_gluten_free = 'True';
/*All restaurants that have dairy free and gluten free options*/


--18 (not checked yet)
SELECT review.review_id, attr.business_id, review.text, review.user_id, business.name from review, business, attr where  review.business_id = business.business_id and attr.business_id = business.business_id and review.stars < 3 and business.is_open = 0; /*reviews for businesses with low reviews and not open*/


--19 (not checked yet)
SELECT review.review_id, attr.business_id, review.text, review.user_id, business.name from review, business, attr where  review.business_id = business.business_id and attr.business_id = business.business_id and review.stars >3 and attr.hairspecializesin_perms = "true"; /*good hair perm places */


--20 (not checked yet)
SELECT attr.business_id, tip.text, business.name, tip.user_id from tip, business, attr where  tip.business_id = business.business_id and attr.business_id = business.business_id and review.stars > 3 and attr.goodformeal_dessert = "true"; /*tips for dessert places */


--21 (not checked yet)
SELECT review.review_id, review.user_id, attr.business_id, review.text, attr.name FROM review, attr WHERE attr.business_id = review.business_id and review.funny > 8 and attr.alcohol = "true"; /*funny yelp reviews about businesses that serve alcohol*/
/*helpful reviews for restuarants good for groups in New York City */

--22 (not checked yet)
SELECT review.review_id, review.user_id, attr.business_id, review.text, attr.name, business.city FROM review, attr, business WHERE attr.business_id = review.business_id and review.business_id = business.business_id and review.helpful > 9 and attr.restaurantsgoodforgroups = "true" and business.city = "New York City";
/*useful reviews for restuarants good for dinner and outdoor seating in Los Angeles */


--23 (not checked yet)
SELECT review.review_id, review.user_id, attr.business_id, review.text, attr.name, business.city FROM review, attr, business WHERE attr.business_id = review.business_id and review.business_id = business.business_id and review.useful > 8 and attr.outdoorseating = "true" and attr.goodformeal_dinner = "true" and business.city = "Los Angeles"; 
/*helpful reviews for curly hair for groups in Houston */


--24 (not checked yet)
SELECT review.review_id, review.user_id, attr.business_id, review.text, attr.name, business.city FROM review, attr, business WHERE attr.business_id = review.business_id and review.business_id = business.business_id and review.helpful > 6 and attr.hairspecializesin_curly = "true" and business.city = "Houston"; 

