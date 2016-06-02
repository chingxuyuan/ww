delimiter //

CREATE PROCEDURE proc_old_fetch_one_advert (device_id int)
BEGIN

    declare has_sim int default 0;
    declare channel_enable int default 0;
    declare area varchar(32) default null;
    declare isp varchar(32) default null;
    declare network varchar(32) default null;
    declare brand varchar(32) default null;
    declare os varchar(32) default null;
    
	declare channel_id int default 0;
    declare advert_id int default 0;
    declare publish_id int default 0;
    
    declare this_time date default null;
    
    set this_time = now();
    
    select device.c_has_sim, device.c_last_area, device.c_isp, device.c_network, device.c_brand, concat(device.c_os, ' ', device.c_version)
		from sys_old_device_info device
        where id = device_id
        into has_sim, area, isp, network, brand, os
	;
        
	select publish.id, publish.c_advert_id, publish.c_chennel_id
		from adv_publish_info publish
		where publish.c_advert_id in
			(
				select id from adv_advert_info
                where c_enable > 0
            )
            and publish.c_advert_id not in
            (
				select c_advert_id from adv_old_task_info
                where c_device_id = device_id
            )
            and publish.c_channel_id in
            (
				select id from sys_channel_info
                where c_enable > 0
            )
            and publish.c_rule_id in
            (
				select id from adv_rule_info
				where c_enable > 0
					and (has_sim > 0 or c_has_sim = 0)
                    and (c_dest_os is null or c_dest_os = os)
                    and (c_isp is null or c_isp = isp)
                    and (c_network is null or c_network = network)
                    and (c_city is null or c_city = area)
                    and (c_brand is null or c_brand = brand)
					and (c_start_date is null or c_start_date <= this_time)
                    and (c_end_date is null or c_end_date >= this_time)
            )
		into publish_id, advert_id, channel_id
	;
    
    if publish_id > 0 and advert_id > 0 and channel_id > 0 then
		insert into adv_old_task_info(c_device_id, c_advert_id, c_publish_id, c_advert_time)
			values(device_id, advert_id, publish_id, this_time)
		;
		select last_insert_id() as task_id;
	else
		select 0 as task_id;
    end if;
    
END

//

CREATE PROCEDURE proc_fetch_one_advert (device_id int)
BEGIN

    declare imsi varchar(32) default null;
    declare channel_enable int default 0;
    declare area varchar(32) default null;
    declare isp varchar(32) default null;
    declare network varchar(32) default null;
    declare brand varchar(32) default null;
    declare os varchar(32) default null;
    
	declare plugin_id int default 0;
	declare channel_id int default 0;
    declare advert_id int default 0;
    declare publish_id int default 0;
    
    declare this_time date default null;
    
    set this_time = now();
    
    select device.c_plugin_id, device.c_imsi, device.c_last_area, device.c_isp, device.c_network, device.c_brand, concat(device.c_os, ' ', device.c_version)
		from sys_device_info device
        where id = device_id
        into plugin_id, imsi, area, isp, network, brand, os
	;
        
	select publish.id, publish.c_advert_id, publish.c_chennel_id
		from adv_publish_info publish
		where publish.c_advert_id in
			(
				select id from adv_advert_info
                where c_enable > 0
            )
            and publish.c_advert_id not in
            (
				select c_advert_id from adv_task_info
                where c_device_id = device_id
            )
            and publish.c_channel_id in
            (
				select id from sys_channel_info
                where c_enable > 0
            )
            and publish.c_rule_id in
            (
				select id from adv_rule_info
				where c_enable > 0
					and (imsi is not null or c_has_sim = 0)
                    and (c_dest_os is null or c_dest_os = os)
                    and (c_isp is null or c_isp = isp)
                    and (c_network is null or c_network = network)
                    and (c_city is null or c_city = area)
                    and (c_brand is null or c_brand = brand)
					and (c_start_date is null or c_start_date <= this_time)
                    and (c_end_date is null or c_end_date >= this_time)
            )
		into publish_id, advert_id, channel_id
	;
    
    if publish_id > 0 and advert_id > 0 and channel_id > 0 then
		insert into adv_task_info(c_device_id, c_plugin_id, c_advert_id, c_publish_id, c_advert_time)
			values(device_id, plugin_id, advert_id, publish_id, this_time)
		;
		select last_insert_id() as task_id;
	else
		select 0 as task_id;
    end if;
    
END

//

delimiter ;
