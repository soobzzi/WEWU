<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <!-- HEADER -->
      <jsp:include page="/header.jsp" />
      <!-- HEADER -->
      <style>
        .container-flex {
          display: flex;
          /* Flexbox를 활용 */
          justify-content: center;
          /* 가로 방향으로 중앙 정렬 */
          align-items: center;
          /* 세로 방향으로 중앙 정렬 */
        }

        .image-container {
          margin-right: 100px;
          /* 이미지와 표 사이에 여백 추가 */
        }

        .modal {
          display: none;
          /* 기본적으로 숨김 */
          position: fixed;
          /* 고정 위치 */
          z-index: 1000;
          /* 가장 위에 표시 */
          left: 0;
          top: 0;
          width: 100%;
          height: 100%;
          overflow: auto;
          /* 스크롤 가능 */
          background-color: rgba(0, 0, 0, 0.4);
          /* 반투명 배경 */
        }

        .modal-content {
          background-color: #fff;
          margin: 15% auto;
          /* 상단에서 15% 떨어짐 */
          padding: 20px;
          border: 1px solid #888;
          width: 80%;
          /* 너비 80% */
          max-width: 600px;
          /* 최대 너비 600px */
        }

        .modal-close {
          color: #aaa;
          float: right;
          font-size: 28px;
          font-weight: bold;
        }

        .modal-close:hover,
        .modal-close:focus {
          color: black;
          text-decoration: none;
          cursor: pointer;
        }

        .randomButton {
          display: flex;
          margin-top: 10px;
          flex-direction: column;
          align-items: center;
        }

        img.randomImage {
          max-width: 200px;
          /* 이미지의 최대 너비 설정 */
          height: auto;
          /* 높이를 자동으로 설정 */
        }

		.hidden {
            display: none;
        }

      </style>
      <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
      <script>
      $(document).ready(function () {
          // 모달창 열기
          $("#btn-open-modal").on("click", function () {
              $("#myModal").show();
          });

          // 모달창 닫기
          $(".modal-close").on("click", function () {
              $("#myModal").hide();
          });

          // 모달창 외부 클릭 시 닫기
          $(window).on("click", function (event) {
              if ($(event.target).is("#myModal")) {
                  $("#myModal").hide();
              }
          });

          var clickCount = 0;
          var maxClicks = 3;

          // 랜덤 식물 선택
          $('#selectRandomPlantButton').on('click', function (event) {
              event.preventDefault(); // 기본 폼 제출 동작 방지

              clickCount++;
              if (clickCount > maxClicks) {
                  alert("더 이상 선택할 수 없습니다.");
                  $(this).prop('disabled', true);
                  return;
              }

              // 랜덤 이미지 요청
              $.ajax({
                  url: "/app/plant/selectRandomPlant",
                  type: "POST",
                  processData: false,
                  contentType: "application/json",
                  dataType: "json",
                  success: function (data) {
                      console.log("Image URL: ", data.plantLevl.levlImg);
                      // 이미지 출력
                      $('#randomImage').attr('src', data.plantLevl.levlImg);
                      $('#plantNo').val(data.plantNo);
                      $('#randomImageContainer').show();
                  },
                  error: function (xhr, status, error) {
                      alert("랜덤뽑기에 실패했습니다!");
                      console.error("Error: ", error);
                  },
                  complete: function (xhr, status) {
                      console.log("Request completed");
                  }
              });
          });

          // 선택 버튼 클릭 시 이름 입력 필드 표시
          $('#choosePlant').on('click', function () {
              $('#plantNameContainer').removeClass('hidden');
          });

          // 저장 버튼 클릭 시 폼 제출
          $('#savePlant').on('click', function (event) {
              event.preventDefault(); // 기본 폼 제출 동작 방지

              // 폼 데이터 수집
              var plantNo = $('#plantNo').val();
              var myPlantName = $('#myPlantName').val();

              // AJAX 요청으로 데이터 전송
              $.ajax({
                  url: "/app/plant/addMyPlant",
                  type: "POST",
                  contentType: "application/json",
                  data: JSON.stringify({
   
                	  plantNo: plantNo,
                      myPlantName: myPlantName
                  }),
                  success: function (response) {
                      alert("식물이 성공적으로 저장되었습니다!");
                      $('#myModal').hide(); // 모달창 닫기
                  },
                  error: function (xhr, status, error) {
                      alert("식물 저장에 실패했습니다.");
                      console.error("Error: ", error);
                  }
              });
          });
      });
  </script>
</head>

<body>

  <!-- 모달 창 HTML 구조 -->
  <div id="myModal" class="modal">
      <div class="modal-content">
          <span class="modal-close">&times;</span>
          <div class="modal-body">
              <div class="container-fluid random">
                  <form id="selectRandomPlant" method="POST">
                      <div class="row randomButton">
                          <div class="row random">
                              <input type="hidden" id="plantNo" name="plantNo" value="">
                              <input type="hidden" id="plantLevlNo" name="plantLevlNo" value="">
                              <div id="randomImageContainer" class="randomImageContainer">
                                  <img id="randomImage" class="randomImage" src="">
                              </div>
                          </div>
                          <div>
                              <p>총 세번의 기회만 존재! </p>
                          </div>
                          <div>
                              <button type="button" id="selectRandomPlantButton" class="btn btn-primary">두근두근 랜덤뽑기!</button>
                          </div>
                          <div>
                              <button type="button" id="choosePlant" class="btn btn-warning mt-2">선택</button>
                          </div>
                          <div id="plantNameContainer" class="form-group hidden mt-3">
                              <label for="myPlantName">나의 식물 이름 :</label>
                              <input type="text" id="myPlantName" name="myPlantName" value="" class="form-control">
                              <button type="submit" id="savePlant" class="btn btn-success btn-sm mt-2 mx-auto">저장</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
      </div>
  </div>

  <div class="main-panel">
      <div class="content-wrapper">
          <!-- NAVI -->
          <jsp:include page="/plant/plantNavi.jsp" />
          <!-- 식물 없을때 랜덤 뽑기로 이동 -->
			<c:if test="${empty myPlant.myPlantNo}">
			  <div class="container mt-4">
			    <div class="row">
			      <div class="col-lg-10 grid-margin stretch-card mx-auto">
			        <div class="card">
			          <button id="btn-open-modal" class="btn btn-primary">식물 뽑으러 가기!</button>
			        </div>
			      </div>
			    </div>
			  </div>
			</c:if>
          <!-- 식물 없을때 랜덤 뽑기로 이동 -->
          <!-- 식물 있을 때 -->
          <c:if test="${!empty myPlant.myPlantNo}">
              <div class="container mt-4">
                  <div class="row">
                      <div class="col-lg-10 grid-margin stretch-card mx-auto">
                          <div class="card">
                              <div class="card-body container-flex">
                                  <div class="image-container">
                                      <img src="${myPlant.plantLevl.levlImg}" width="200">
                                  </div>
                                  <div class="table-container">
                                      <table class="table table-hover">
                                          <thead>
                                              <tr>
                                                  <td>나의 식물 이름</td>
                                                  <td id="myPlantName">${myPlant.myPlantName}</td>
                                                  <td>
                                                      <div class="editable" id="editable-text">
                                                          <button type="button" id="edit" class="btn btn-outline-success btn-sm">수정</button>
                                                          <button type="button" id="save" class="btn btn-outline-success btn-sm" style="display:none;">저장</button>
                                                      </div>
                                                  </td>
                                              </tr>
                                          </thead>
                                          <tbody>
                                              <tr>
                                                  <td>현재단계</td>
                                                  <td>${myPlant.plantLevl.plantLevl}</td>
                                                  <td colspan="2"></td>
                                              </tr>
                                              <tr>
                                                  <td>변화형태</td>
                                                  <td><img src="${myPlant.plantLevl.levlImg}"></td>
                                                  <td colspan="2"></td>
                                              </tr>
                                              <tr>
                                                  <td>현재경험치</td>
                                                  <td>${myPlant.myPlantExp}</td>
                                                  <td colspan="2"></td>
                                              </tr>
                                              <tr>
                                                  <td colspan="2"></td>
                                                  <td class="text-right"><button type="button" id="delete" class="btn btn-success btn-sm">삭제</button></td>
                                                  <c:if test="${myPlant.myPlantExp >= 100}">
                                                      <td class="text-right"><button type="button" id="delete" class="btn btn-success btn-sm">식물저장(기부)</button></td>
                                                  </c:if>
                                              </tr>
                                          </tbody>
                                      </table>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
          </c:if>
          <!-- 식물 없을때 랜덤 뽑기로 이동 -->
      </div>
  </div>

  <!-- FOOTER -->
  <jsp:include page="/footer.jsp" />
  <!-- FOOTER -->

</body>

</html>