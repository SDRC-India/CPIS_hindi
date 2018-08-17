
<!-- Bootstrap JS -->
<script src="resources/js/bootstrap.min.js"></script>
<!-- Smooth Scroll -->
<script>
$('#theCarousel').carousel({
   interval: 4000
})

$('.multi-item-carousel .item').each(function(){
    var next = $(this);
    var last;
    for (var i=0;i<5;i++) {
        next=next.next();
        if (!next.length) {
            next = $(this).siblings(':first');
        }

        last=next.children(':first-child').clone().appendTo($(this));
    }
    last.addClass('rightest');
   
});
</script>
<!-- Smooth Scroll -->

<!--<script src="resources/js/lightbox.min.js"></script>
<!-- All JS plugin Triggers -->

<script src="resources/js/angular.min.js"></script>
<script src="resources/js/ng-libs/ticker.js"></script>
<script src="resources/js/lightbox-plus-jquery.min.js"></script>

<script src="resources/js/ng_app.js"></script>