function add_table_row(owner, index) {
	var row = document.createElement("TR");

	for (var child = owner.firstChild; child != null; child = child.nextSibling) {
		if (index <= 0) {
			owner.insertBefore(row, child);
			return row;
		}

		index--;
	}

	owner.appendChild(row);
	return row;
}

function add_table_cell(row, text) {
	var td = document.createElement("TD");
	row.appendChild(td);
	td.innerText = text == null ? "" : text;

	return td;
}

function add_table_link_cell(row, text, callback) {
	var td = document.createElement("TD");
	row.appendChild(td);
	var a = document.createElement("A");
	a.innerText = text;
	a.href = "javascript:;";
	a.onclick = callback;
	td.appendChild(a);

	return td;
}

function select_table_row(owner, row, normal_color, selected_color) {
	for (var child = owner.firstChild; child != null; child = child.nextSibling) {
		if (child == row) {
			child.style.backgroundColor = selected_color;
		} else {
			child.style.backgroundColor = normal_color;
		}
	}
}

function get_table_selected_row(owner, selected_color) {
	for (var child = owner.firstChild; child != null; child = child.nextSibling) {
		if (child.style.backgroundColor == selected_color) {
			return child;
		}
	}

	return null;
}

function clear_data_list(table){
	while(table.childNodes.length > 0){
		table.removeChild(items.firstChild);
	}
	
	$("#total_record")[0].innerText = 0;
	$("#total_page").val(0);
	$("#current_page_index")[0].innerText = "0/0";
	$("#pageindex").val(1);
}

function update_page_info(data){
	$("#total_record")[0].innerText = data.total;
	$("#total_page").val(data.pageCount);
	$("#current_page_index")[0].innerText = data.pageIndex + "/" + data.pageCount;
	$("#pageindex").val(data.pageIndex);
}

function head_page() {
	var ctrl = $("#pageindex");
	ctrl.val(1);
	
	list();
}

function prev_page() {
	var ctrl = $("#pageindex");
	var index = ctrl.val() * 1;
	
	if(index <= 1){
		ctrl.val(1);
	}else{
		index -= 1;
		ctrl.val(index);
	}
	
	list();
}

function next_page() {
	var ctrl = $("#pageindex");
	var index = ctrl.val() * 1;
	var total = $("#total_page").val() * 1;
	
	if(index >= total && index != 1){
		ctrl.val(total);
	}else{
		index += 1;
		ctrl.val(index);
	}
	
	list();
}

function last_page() {
	var ctrl = $("#pageindex");
	var total = $("#total_page").val() * 1;
	
	ctrl.val(total < 1 ? 1 : total);
	
	list();
}

function goto_page() {
	var ctrl = $("#pageindex");
	var index = ctrl.val() * 1;
	var total = $("#total_page").val() * 1;
	
	if(index <= 1){
		ctrl.val(1);
	}else if(index >= total){
		ctrl.val(total);
	}else{
		ctrl.val(index);
	}
	
	list();
}
