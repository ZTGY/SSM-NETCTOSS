<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/10/22
  Time: 下午4:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        //查看所有状态正常的主题帖
        function selectAllPublicTopicPost(pageNum){
            var url = "/guestbook/rs/topicpost/selectallpublictopicpost";
            var json = JSON.stringify(pageNum);
            $.ajax({
                url:url,
                type:'POST',
                async:true,
                data: json,
                contentType:"application/json",
                dataType:'json',
                success:function(data,textStatus,jqXHR){
                    var firstNum = pageNum - 2;
                    var secondNum = pageNum -1;
                    var thirdNum = pageNum;
                    var fourthNum = pageNum + 1;
                    var fifthNum = pageNum + 2;
                    var prePage = pageNum - 1;
                    var nextPage = pageNum + 1;

                    if(pageNum < 4 || data.pages < 6){
                        firstNum = 1;
                        secondNum = 2;
                        thirdNum = 3;
                        fourthNum = 4;
                        fifthNum = 5;
                    }
                    else if(pageNum > data.pages-2){
                        firstNum = data.pages-4;
                        secondNum = data.pages-3;
                        thirdNum = data.pages-2;
                        fourthNum = data.pages-1;
                        fifthNum = data.pages;
                    }
                    if(data.isFirstPage){
                        prePage = 1;
                    }
                    if(data.isLastPage ){
                        nextPage = data.pages;
                    }
                    var index_page = "<li><a onclick='selectAllPublicTopicPost(1)' aria-label='Previous'>" +
                            "<span aria-hidden='true'><<</span></a></li>"
                            +"<li><a onclick='selectAllPublicTopicPost("+prePage+")' aria-label='Previous'>" +
                            "<span aria-hidden='true'><</span></a></li>"
                            +"<li><a onclick='selectAllPublicTopicPost("+firstNum+")'>"+firstNum+"</a></li>";
                    if(secondNum <= data.pages){
                        index_page = index_page + "<li><a onclick='selectAllPublicTopicPost("+secondNum+")'>"+secondNum+"</a></li>";
                    }
                    if(thirdNum <= data.pages){
                        index_page = index_page + "<li><a onclick='selectAllPublicTopicPost("+thirdNum+")'>"+thirdNum+"</a></li>";
                    }
                    if(fourthNum <= data.pages){
                        index_page = index_page + "<li><a onclick='selectAllPublicTopicPost("+fourthNum+")'>"+fourthNum+"</a></li>";
                    }
                    if(fifthNum <= data.pages){
                        index_page = index_page + "<li><a onclick='selectAllPublicTopicPost("+fifthNum+")'>"+fifthNum+"</a></li>";
                    }
                    index_page = index_page +"<li><a onclick='selectAllPublicTopicPost("+nextPage+")' aria-label='Next'><span aria-hidden='true'>></span></a></li>"
                            +"<li><a onclick='selectAllPublicTopicPost("+data.pages+")' aria-label='Next'><span aria-hidden='true'>>></span></a></li>";
                    $("#index_page").html(index_page);
                    //以下内容不重要
                    var tp = "";
                    jQuery.each( data.list,function(i,item){
                        tp = tp + "<div class='col-lg-4'><a href='/guestbook/contents.html?topicPostId="
                                +item.id
                                +"' target='_blank'>"
                                +item.title
                                +"</a></div><div class='col-lg-2'>"
                                +item.keyword
                                +"</div><div class='col-lg-2'>"
                                +vagueTime(new Date(item.time).toLocaleString())
                                +"</div><div class='col-lg-2'><a href='/guestbook/cust_info.html?uid="
                                +item.userId
                                +"' target='_blank'>"
                                +item.userName
                                +"</a></div><div class='col-lg-1'>"
                                +item.replyNumber
                                +"<br/><br/></div>";
                    });
                    $("#topic_list").html(tp);
                }
            })
        }
    </script>
</head>
<body>

</body>
</html>
