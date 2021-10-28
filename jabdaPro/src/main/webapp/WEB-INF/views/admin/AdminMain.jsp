<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String rootPath = request.getContextPath(); %>
   
<!doctype html>
<html lang="ko">
  <head>
  	<title>MainPage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<jsp:include page="/include/css.do"/>
	

  </head>
  <body>
		
		<div class="wrapper d-flex align-items-stretch">
		<!-- Sidebar_admin Start -->
		<jsp:include page="/include/adminsidebar.do"/>

        <!-- Page Content  -->
      	<div id="content" class="p-4 p-md-5 pt-5">
        	<div class="row">
                  <div class="col-xl-6">
                      <div class="card mb-4">
                          <div class="card-header">
                              <i class="fas fa-chart-area me-1"></i>
                              게시판 통계
                          </div>
                          <div class="card-body"><canvas id="myLineChart" width="100%" height="40"></canvas></div>
                      </div>
                  </div>
                  <div class="col-xl-6">
                      <div class="card mb-4">
                          <div class="card-header">
                              <i class="fas fa-chart-bar me-1"></i>
                              방문자 통계
                          </div>
                          <div class="card-body"><canvas id="myBarChart" width="100%" height="40"></canvas></div>
                      </div>
                  </div>
                </div>
        		<div class="row">
                  <div class="col-xl-6">
                      <div class="card mb-4">
                          <div class="card-header">
                              <i class="fas fa-chart-area me-1"></i>
                              최근 게시글
                          </div>
                          <div class="card-body">
                            <table id="QnatablesSimple">
                              <thead>
                                <tr>
                                  <th>작성자</th>
                                  <th>제목</th>
                                  <th>작성일</th>
                                </tr>
                              </thead>
                              <tbody>
                              	
                              </tbody>
                            </table>
                          </div>
                      </div>
                  </div>
                  <div class="col-xl-6">
                      <div class="card mb-4">
                          <div class="card-header">
                              <i class="fas fa-chart-bar me-1"></i>
                              회원현황
                          </div>
                          <div class="card-body">
                            <table id="datatablesSimple1">
                              <thead>
                                <tr>
                                  <th>ID</th>
                                  <th>Name</th>
                                  <th>회원구분</th>
                                  <th>가입일</th>
                                </tr>
                              </thead>
                              <tfoot>
                                <tr>
                                  <th>ID</th>
                                  <th>Name</th>
                                  <th>회원구분</th>
                                  <th>가입일</th>
                                </tr>
                              </tfoot>
                              <tbody>
                                
                              </tbody>
                            </table>
                          </div>
                      </div>
                  </div>
              </div>
		        

      	</div>
    

    </div>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
	<script src="<%=rootPath %>/assets/js/datatables.js"></script>
    <jsp:include page="/include/js.do"/>
    <script type="text/javascript">
          var context = document
                .getElementById('myLineChart')
                .getContext('2d');
            var myChart = new Chart(context, {
                type: 'line', // 차트의 형태
                data: { // 차트에 들어갈 데이터
                    labels: [
                        //x 축
                        'Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday'
                    ],
                    datasets: [
                        { //데이터
                            label: '게시판 통계', //차트 제목
                            fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
                            data: [
                                21,19,25,20,23,26,25 //x축 label에 대응되는 데이터 값
                            ],
                            backgroundColor: [
                                //색상
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 159, 64, 0.2)'
                            ],
                            borderColor: [
                                //경계선 색상
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)'
                            ],
                            borderWidth: 1 //경계선 굵기
                        }/* ,
                        {
                            label: 'test2',
                            fill: false,
                            data: [
                                8, 34, 12, 24
                            ],
                            backgroundColor: 'rgb(157, 109, 12)',
                            borderColor: 'rgb(157, 109, 12)'
                        } */
                    ]
                },
                options: {
                    scales: {
                        yAxes: [
                            {
                                ticks: {
                                    beginAtZero: true
                                }
                            }
                        ]
                    }
                }
            });
        </script>
        <script type="text/javascript">
          var context = document
                .getElementById('myBarChart')
                .getContext('2d');
            var myChart = new Chart(context, {
                type: 'bar', // 차트의 형태
                data: { // 차트에 들어갈 데이터
                    labels: [
                        //x 축
                    	'Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday'
                    ],
                    datasets: [
                        { //데이터
                            label: '게시판 통계', //차트 제목
                            fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
                            data: [
                                45,21,27,20,23,26,25 //x축 label에 대응되는 데이터 값
                            ],
                            backgroundColor: [
                                //색상
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 159, 64, 0.2)'
                            ],
                            borderColor: [
                                //경계선 색상
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)'
                            ],
                            borderWidth: 1 //경계선 굵기
                        }/* ,
                        {
                            label: 'test2',
                            fill: false,
                            data: [
                                8, 34, 12, 24
                            ],
                            backgroundColor: 'rgb(157, 109, 12)',
                            borderColor: 'rgb(157, 109, 12)'
                        } */
                    ]
                },
                options: {
                    scales: {
                        yAxes: [
                            {
                                ticks: {
                                    beginAtZero: true
                                }
                            }
                        ]
                    }
                }
            });
        </script>
  </body>
</html>