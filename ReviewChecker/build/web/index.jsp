<%-- 
    Document   : index
    Created on : Jun 17, 2018, 8:43:23 PM
    Author     : Milinda Arambawela
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Review Checker</title>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link href="assert/css/bootstrap.min.css" rel="stylesheet">
        <link href="assert/css/style.css" rel="stylesheet">
    </head>
    <body>
        
            <%

	        Connection con = db.DBCon.getDbCon();
                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM sentences WHERE checked=0");               

	    %>

	 

    <div class="container-fluid">
	<div class="row">
            <div class="col-md-3"></div>
		<div class="col-md-6">
                    <h1>Review Checker</h1>  </br>
                    <form role="form" action="ReviewUpdate">
                            
                            <%
                                if(rs.first()){
                                
                                    %>
                            
                            <div class="form-group">
					 
					<label for="senetence">
						Sentence
					</label>
                                <h2> <%=rs.getString("sentences") %> </h2>
				</div><hr/>
				<div class="form-group">
					 
					<label for="sentiment">
						Sentiment - <%=rs.getString("sentiment") %>
					</label>
                                        <input class="" type="hidden" name="sentId" value="<%=rs.getString("idsent") %>">
                                        </br>
                                    <select name="sentiment" class="btn-block">
                                        <option value="Good">Good</option>
                                        <option value="Neutral">Neutral</option>
                                        <option value="Bad">Bad</option>
                                    </select>					
				</div>
				<hr/>
				<div class="checkbox">	
                                    <label for="keywords">
						Keywords
                                    </label> <br>
                                    <%
                                        ResultSet rs2 = con.createStatement().executeQuery("SELECT * FROM hotel_review.keywords where idSent='"+rs.getString("idsent")+"'");
                                    
                                        while(rs2.next()){
                                        %>
                                            <label>
                                                <input type="checkbox" name="keyword[]" value="<%=rs2.getString("keyword") %>"> <%=rs2.getString("keyword") %>
                                            </label>
                                            &nbsp; &nbsp;
                                        <%
                                        }
                                        %>
				</div>
                                <hr/>
                                <div class="form-group">
					 
					<label for="new_keywords">
						Add Keywords
					</label>
                                        </br>
                                        <input class="btn-block" type="text" name="keywords" value="">
                                        </br>
                                        <label>Separate by comma "," </label>
				</div>
                                
                                
				
				<button type="submit" class="btn btn-primary btn-block">
					Submit
				</button>
                                <a class="btn btn-danger btn-block" href="">Skip</a>

                                <%
                                }
                                %>
			</form>
		</div>
            <div class="col-md-3"></div>
	</div>
    </div>

        <script src="assert/js/jquery.min.js"></script>
        <script src="assert/js/bootstrap.min.js"></script>
        <script src="assert/js/scripts.js"></script>
  </body>
</html>