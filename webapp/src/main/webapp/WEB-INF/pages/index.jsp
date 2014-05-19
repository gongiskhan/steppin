<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <img style="float:left; position:relative; top:0.7em;" src="/resources/images/logo_small.png"/><a class="navbar-brand" href="/index.html">steppin</a>

        </div>
        <a href="/logout" style="float:right;top:0.5em;position:relative;" title="Logout"><span class="glyphicon glyphicon-log-out"></span></a>
    </div>
</div>
<hr/>
<div class="container-fluid">
    <div class="row">
        <h1 class="page-header">Dashboard</h1>
        <span class="error"></span>
        <span class="message"></span>
        <div class="row placeholders">
            <div class="col-xs-6 col-sm-3 placeholder">
                <div id='chart_div' style="left:4em;"></div>
                <h4>Average Speed</h4>
                <span class="text-muted">(data collected for 1 week)</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder" style="top:1em;">
                <span style="font-size:3.5em;" id="weekDistance">0 meters</span>
                <h4>Distance</h4>
                <span class="text-muted">(data collected for 1 week)</span>
            </div>
        </div>
        <h2 class="sub-header">Log Entries</h2>
        <hr/>
        <h3>New</h3>
        <div class="well">
            <div class="form-group">
                <div>
                    <label>Date (yyyy/mm/dd)</label>
                    <div class='input-group date' id='datetimepicker' data-date-format="YYYY/MM/DD">
                        <input type='text' class="form-control" id="date"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-date"></span>
                        </span>
                    </div>
                </div>
                <div>
                    <label>Distance (meters)</label>
                    <div class='input-group'>
                        <input type='number' class="form-control" id="distance"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-road"></span>
                        </span>
                    </div>
                </div>
                <div>
                    <label>Duration (seconds)</label>
                    <div class='input-group' id='time'>
                        <input type='number' class="form-control" id="duration"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-time"></span>
                        </span>
                    </div>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-default" id="logEntrySubmit">Submit</button>
                </div>
            </div>
        </div>
        <hr/>
        <h3>List</h3>
        <div class="table-responsive">
            <table class="table table-striped" id="logEntriesTable"></table>
        </div>
        <hr/>
        <h3>List by Date</h3>
        <div class="form-group">
            <div>
                <label>From (yyyy/mm/dd)</label>
                <div class='input-group date' id='datetimepicker_from' data-date-format="YYYY/MM/DD">
                    <input type='text' class="form-control" id="from"/>
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-date"></span>
                            </span>
                </div>
            </div>
            <div>
                <label>To (yyyy/mm/dd)</label>
                <div class='input-group date' id='datetimepicker_to' data-date-format="YYYY/MM/DD">
                    <input type='text' class="form-control" id="to"/>
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-date"></span>
                            </span>
                </div>
            </div>
            <div class="btn-group">
                <button type="button" class="btn btn-default" id="fromToFindButton">Find</button>
            </div>
        </div>
    </div>
</div>
<link href="/resources/css/jquery.dataTables.css" rel="stylesheet">
<script type="text/javascript" src="/resources/js/docs.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript" src="/resources/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="/resources/js/jquery-dateFormat.js"></script>
<script type="text/javascript">

    google.load('visualization', '1', {packages:['gauge']});
    google.setOnLoadCallback(updateSpeedGauge);
    function updateSpeedGauge() {
        $.ajax({
            url:'http://localhost:8500/user/<sec:authentication property="principal.id"/>/weeklyData?weekStart='+$.format.date(new Date(new Date().getTime()-604800000), 'yyyy/MM/dd'),
            type:'GET',
            complete:function(r){
                if(r.status == 200){

                    var parsedResponse = JSON.parse(r.responseText);

                    var data = google.visualization.arrayToDataTable([
                        ['Label', 'Value'],
                        ['Km/H', Math.round(parsedResponse.averageSpeedInKmH * 10000)/10000]
                    ]);

                    var options = {
                        width: 400, height: 160,
                        minorTicks: 5
                    };

                    var chart = new google.visualization.Gauge(document.getElementById('chart_div'));
                    chart.draw(data, options);
                    $('#weekDistance').html(parsedResponse.distanceInMeters + ' meters');
                }else{
                    $('.error').html('Error: '+r.statusText).show('fast');
                }
            }
        });
    }

    function updateLogEntriesData(){
        $.ajax({
            url:'http://localhost:8500/user?id=<sec:authentication property="principal.id"/>',
            type:'GET',
            complete:function(r){
                if(r.status == 200){
                    updateDataTable(JSON.parse(r.responseText || "{\"logEntries\":[]}").logEntries);
                }else{
                    $('.error').html('Error: '+r.statusText).show('fast');
                }
            }
        });
    }
    function updateDataTable(entries){
        $('.error').html('').hide('fast');
        var data = preparedData = [];
        for( var i= 0, len = entries.length; i < len; i++ ){
            var entry = entries[i],
                    entryArray = [];
            entryArray.push(entry.date);
            entryArray.push(entry.distanceInMeters);
            entryArray.push(entry.timeInMilliseconds/1000);
            //Calculate average speed in km/h
            var averageSpeed = Math.round( ((entry.distanceInMeters/1000) / (entry.timeInMilliseconds/1000/60/60)) * 100) / 100;
            entryArray.push( averageSpeed );
            preparedData.push(entryArray);
        }
        var tableData = [[1,'12/12/1234 00:66',9,43]];
        var rowCount = 0;
        $('#logEntriesTable').dataTable({
            "data": preparedData,
            "columns": [
                {title:'Date'},
                {title:'Distance (meters)'},
                {title:'Duration (seconds)'},
                {title:'Average Speed (km/hour)'},
                {"bSearchable": false, "bSortable": false, "mRender": function (data, type, full) {
                    return '<a href="'+rowCount+++'" class="removeLogEntryButton">X</a>';
                }}
            ]
        });
        $('.removeLogEntryButton').off('click');
        $('.removeLogEntryButton').on('click',function(e){
            e.preventDefault();
            $.ajax({
                url:'http://localhost:8500/user/<sec:authentication property="principal.id"/>/logEntries?logEntryIndex='+$(e.target).attr('href'),
                type:'DELETE',
                complete:function(r){
                    if(r.status == 200){
                        $('.error').hide('fast').html('');
                        $('.message').html('Entry deleted.').show('fast');
                        $('#logEntriesTable').dataTable().fnDestroy();
                        updateLogEntriesData();
                        updateSpeedGauge();
                        setTimeout(function(){
                            $('.message').hide('fast').html('');
                        },2000);
                    }else{
                        $('.message').hide('fast').html('');
                        $('.error').html('Error: '+r.statusText).show('fast');
                    }
                }
            });
        });
    }
    $(function(){

        $('#datetimepicker').datetimepicker({
            pickTime: false
        });
        $('#datetimepicker_from').datetimepicker({
            pickTime: false
        });
        $('#datetimepicker_to').datetimepicker({
            pickTime: false
        });

        $('#logEntrySubmit').on('click',function(e){
            $.ajax({
                url:'http://localhost:8500/user/<sec:authentication property="principal.id"/>/logEntries',
                type:'POST',
                contentType: 'application/json',
                data:JSON.stringify({
                    date: $('#date').val(),
                    distanceInMeters: $('#distance').val(),
                    timeInMilliseconds: ($('#duration').val()*1000)
                }),
                complete:function(r){
                    if(r.status == 201){
                        $('.error').html('').hide('fast');
                        setTimeout(function(){
                            $('#logEntriesTable').dataTable().fnDestroy();
                            updateLogEntriesData();
                            updateSpeedGauge();
                        },1000);
                    }else{
                        $('.message').html('').hide('fast');
                        $('.error').html('Error: '+r.statusText).show('fast');
                    }
                }
            });
        });
        $('#fromToFindButton').on('click',function(e){
            $.ajax({
                url:'http://localhost:8500/logEntry?userId=<sec:authentication property="principal.id"/>&from='+$('#from').val()+'&to='+$('#to').val(),
                type:'GET',
                complete:function(r){
                    if(r.status == 200){
                        $('#logEntriesTable').dataTable().fnDestroy();
                        updateDataTable(JSON.parse(r.responseText));
                    }else{
                        $('.error').html('Error: '+r.statusText).show('fast');
                    }
                }
            });
        });

        updateLogEntriesData();

    });
</script>
