<%@page import="com.user.UserTO"%>
<%@page import="com.test.testTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath(); %>

<%
	ArrayList<UserTO> datas = (ArrayList)request.getAttribute("user_list");

	StringBuilder user = new StringBuilder();
	
	for( UserTO to : datas) {
		String seq = to.getSeq();
		String nickname = to.getNickname();
		String email = to.getEmail();
		String rank = to.getRank();
		String date = to.getDate();
		
		user.append("<tr>");
		user.append("	<td>" + seq + "</td>");
		user.append("	<td>" + nickname + "</td>");
		user.append("	<td>" + email + "</td>");
		user.append("	<td>" + date + "</td>");
		user.append("<td>");
		user.append("<form novalidate action='rank_modify_ok.do?email=" + email + "' method='post' name='redit' class='ng-untouched ng-pristine ng-valid'>");
			user.append("<select name='rank' id='rank' class='form-select btn-sm btn-outline-primary'>");
				user.append("<option selected>" + rank + "</option>");
				user.append("<option>--------</option>");
				user.append("<option value='general'>general</option>");
				user.append("<option value='admin'>admin</option>");
			user.append("</select>");
			user.append("<div style='margin-top:1px; float:bottom;'><a type='button' id='btnchange' class='btn btn-outline-primary'>변경</a></div>");
		user.append("</form>");
		user.append("</td>");
		user.append("<tr>");
	}
%>
   
<!doctype html>
<html lang="ko">
  <head>
  	<title>MainPage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<jsp:include page="/include/css.do"/>
	<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />

  </head>
  <body>
		
		<div class="wrapper d-flex align-items-stretch">
		<jsp:include page="/include/adminsidebar.do"/>

        <!-- Page Content  -->
      	<div id="content" class="p-4 p-md-5 pt-5">
        
        <div class="row">
       		<div class="col-xl-12">
            <div class="card mb-4">
                <div class="card-header">
                    <i class="fas fa-chart-bar me-1"></i>
                    유저관리
                </div>
                <div class="card-body">
                  <table id="UsertablesSimple">
                    <thead>
                      <tr>
                      	<th>Num</th>
                        <th>Nickname</th>
                        <th>Email(ID)</th>
                        <th>Join Day</th>
                        <th>기능</th>
                      </tr>
                    </thead>
                    
                    <tbody>
                      <%=user %>
                    </tbody>
                  </table>
                </div>
            </div>
        </div> 
        </div>
        
        
       
       
		        

      </div>
    

    </div>
    
    <div class="modal fade" id="my_modal" tabindex="-1" role="dialog" aria-labelledby="my_modalLabel">
	<div class="modal-dialog" role="dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel">Modal Title</h4>
        </div>
        	<div class="modal-body">
           		Modal Body
            	<input type="hidden" name="hiddenValue" id="hiddenValue" value="" />
        	</div>
        	<div class="modal-footer">
            	<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
            	<button type="button" class="btn btn-primary">Yes</button>
        	</div>
    	</div>
	</div>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
	<script src="<%=rootPath %>/assets/js/datatables.js"></script>
    <jsp:include page="/include/js.do"/>
    
    <script type="text/javascript">
    $(document).ready(function() {

    	  $('a[data-toggle=modal], button[data-toggle=modal]').click(function () {

    	    var data_id = '';

    	    if (typeof $(this).data('id') !== 'undefined') {

    	      data_id = $(this).data('id');
    	    }

    	    $('#my_element_id').val(data_id);
    	  })
    	});
    </script>
  </body>
</html>