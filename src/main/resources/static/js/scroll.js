$(function(){
    // 스크롤 시 header fade-in
    $(document).on('scroll', function(){

        if($(window).scrollTop() > 100){
            $("#header").removeClass("deactive");
            $("#header").addClass("active");
            $("#changeScroll_logo").removeClass("nav-text");
            $("#changeScroll_logo").addClass("nav-scroll-text");
            $("#changeScroll").removeClass("nav-text");
            $("#changeScroll").addClass("nav-scroll-text");
            $("#changeScroll2").removeClass("nav-text");
            $("#changeScroll2").addClass("nav-scroll-text");
            $("#changeScroll3").removeClass("nav-text");
            $("#changeScroll3").addClass("nav-scroll-text");
            $("#changeScroll4").removeClass("nav-text");
            $("#changeScroll4").addClass("nav-scroll-text");
            $("#changeScroll5").removeClass("nav-text");
            $("#changeScroll5").addClass("nav-scroll-text");
            $("#changeScroll6").removeClass("nav-text");
            $("#changeScroll6").addClass("nav-scroll-text");
            $("#changeScroll7").removeClass("nav-text");
            $("#changeScroll7").addClass("nav-scroll-text");
            $("#changeScroll8").removeClass("nav-text");
            $("#changeScroll8").addClass("nav-scroll-text");

        }else{
            $("#header").removeClass("active");
            $("#header").addClass("deactive");
            $("#changeScroll_logo").removeClass("nav-scroll-text");
            $("#changeScroll_logo").addClass("nav-text");
            $("#changeScroll").removeClass("nav-scroll-text");
            $("#changeScroll").addClass("nav-text");
            $("#changeScroll2").removeClass("nav-scroll-text");
            $("#changeScroll2").addClass("nav-text");
            $("#changeScroll3").removeClass("nav-scroll-text");
            $("#changeScroll3").addClass("nav-text");
            $("#changeScroll4").removeClass("nav-scroll-text");
            $("#changeScroll4").addClass("nav-text");
            $("#changeScroll5").removeClass("nav-scroll-text");
            $("#changeScroll5").addClass("nav-text");
            $("#changeScroll6").removeClass("nav-scroll-text");
            $("#changeScroll6").addClass("nav-text");
            $("#changeScroll7").removeClass("nav-scroll-text");
            $("#changeScroll7").addClass("nav-text");
            $("#changeScroll8").removeClass("nav-scroll-text");
            $("#changeScroll8").addClass("nav-text");
        }
    })

});