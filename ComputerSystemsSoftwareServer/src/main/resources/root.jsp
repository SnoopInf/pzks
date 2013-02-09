<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Computer Systems Software Server</title>
<link rel="stylesheet" href="libs/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" href="css/root.css" />
<script type="text/javascript" src="libs/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/root.js"></script>
<script type="text/javascript" src="js/navigation.js"></script>
<script type="text/javascript">
  mxBasePath = 'libs/mxgraph';
</script>
<script type="text/javascript" src="libs/mxgraph/mxClient.js"></script>
</head>
<body>
    <div class="page-header" style="margin-bottom: 0">
        <h1>Computer Systems Software <small>Laboratory work #1</small></h1>
    </div>
    <div id="central">
	    <div id="viewer">
	        <div id="navigationbar">
               <ul id="navigation" class="breadcrumb">
                   <li class="active"><a href="#">Step 1</a> <span class="divider">/</span></li>
                   <li><a href="#">Step 2</a> <span class="divider">/</span></li>
                   <li><a href="#">Step 3</a> <span class="divider">/</span></li>
                   <li>Step 4</li>
                   <li style="float:right" id="currentstep">tst</li>
               </ul>
	        </div>
	        <div id="menubar"></div>
	        <div>
	            <div id="left">
	                <img src="images/left.png"></img>
	            </div>
	            <div id="right">
                    <img src="images/right.png"></img>
                </div>
	            <div id="view">
	               <div id="stepouter">
	                   <div id="fadeleft"></div>
		               <div class="step" style="background:grey"></div>
	                   <div class="step" style="background:blue"></div>
	                   <div class="step" style="background:yellow"></div>
	                   <div class="step" style="background:red"></div>
	                   <div id="faderight"></div>
	               </div>
	            </div>
	        </div>
	    </div>
    </div>
    <footer>
        <div id="copyright">&copy; Yurii Shylov 2013</div>
    </footer>
</body>
</html>
