SELECT attributes_id, business_id, name FROM business WHERE city="Houston";
SELECT attributes_id, business_id, name FROM business WHERE stars > 4.8 AND review_count > 500;
FROM review as r /*left table*/
INNER JOIN photo as p /*list reviews and photos from the same business */
ON r.business_id = p.business_id;


