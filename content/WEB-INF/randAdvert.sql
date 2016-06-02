delimiter //

CREATE PROCEDURE proc_rand_one_advert (p_type int)
BEGIN

	 declare r_id int default 0;
	 
	 
SELECT t1.id FROM adv_advert_info AS t1
JOIN
(SELECT
    ROUND(
        RAND() * ((SELECT MAX(id) FROM adv_advert_info)-(SELECT MIN(id) FROM adv_advert_info))
        + (SELECT MIN(id) FROM adv_advert_info)
    ) AS id
) AS t2
WHERE t1.id>=t2.id and t1.c_type = p_type

ORDER BY t1.id LIMIT 1;
		
		select r_id as task_id;
	
END

//

delimiter ;
