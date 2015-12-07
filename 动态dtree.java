//后台
 Sys_menu root =  this.userinfoServiceImpl.getRootMenu("0000");
        List<MyTree> list = new ArrayList<MyTree>();
        MyTree d_root = new MyTree();
        d_root.setId(root.getMenu_id());
        d_root.setPid("-1");
        d_root.setName(root.getMenu_name());
        d_root.setUrl(root.getUrl());
        d_root.setTitle("");
        d_root.setTarget(root.getTarget());
		list.add(d_root);//添加根节点
		//获取二级节点
		List<Sys_menu> menu_2 = this.userinfoServiceImpl.getMenu(root.getMenu_id());
		System.out.println(menu_2.size());
		if(menu_2.size()!=0){
		for(int i =0;i<menu_2.size();i++){
			System.out.println(menu_2.get(i).getMenu_name());
			 MyTree branch_2 = new MyTree();
			 branch_2.setId(menu_2.get(i).getMenu_id());
			 branch_2.setPid(menu_2.get(i).getParent_id());
			 branch_2.setName(menu_2.get(i).getMenu_name());
			 branch_2.setUrl(menu_2.get(i).getUrl());
			 branch_2.setTitle("");
			 branch_2.setTarget(menu_2.get(i).getTarget());
			list.add(branch_2);
			//获取三级节点
			List<Sys_menu> menu_3 = this.userinfoServiceImpl.getMenu(menu_2.get(i).getMenu_id());
			System.out.println(menu_3.size());
			if(menu_3.size()!=0){
			  for(int j =0;j<menu_3.size();j++){
				  System.out.println(menu_3.get(j).getMenu_name());
				MyTree branch_3 = new MyTree();
				branch_3.setId(menu_3.get(j).getMenu_id());
				branch_3.setPid(menu_3.get(j).getParent_id());
				branch_3.setName(menu_3.get(j).getMenu_name());
				branch_3.setUrl(menu_3.get(j).getUrl());
				branch_3.setTitle("");
				branch_3.setTarget(menu_3.get(j).getTarget());
				list.add(branch_3);
				//获取四级节点(目前最多4级)
				List<Sys_menu> menu_4 = this.userinfoServiceImpl.getMenu(menu_3.get(j).getMenu_id());
				System.out.println(menu_4.size());
				if(menu_4.size()!=0){
				 for(int k =0;k<menu_4.size();k++){
					 System.out.println(menu_4.get(k).getMenu_name());
					MyTree branch_4 = new MyTree();
					branch_4.setId(menu_4.get(k).getMenu_id());
					branch_4.setPid(menu_4.get(k).getParent_id());
					branch_4.setName(menu_4.get(k).getMenu_name());
					branch_4.setUrl(menu_4.get(k).getUrl());
					branch_4.setTitle("");
					branch_4.setTarget(menu_4.get(k).getTarget());
					list.add(branch_4);
				 }
				}
			  }
			}
		}
		}
		request.setAttribute("tree_list", list);


//前台
<div class="dtree" id="dtree">
 					<script type="text/javascript">
 				        	d = new dTree('d');
           	        	         <%
           	  	        	          List<MyTree> list= (List<MyTree>) request.getAttribute("tree_list");
             	  	        	      for(int i=0;i<list.size();i++){
                 	  	        	  String id= list.get(i).getId();
                 	  	        	  String pid = list.get(i).getPid();
        	                          String name = list.get(i).getName();
        	                          String url = list.get(i).getUrl();
        	                          String title = list.get(i).getTitle();
        	                          String target = list.get(i).getTarget();
                                  %>
                           d.add(<%=id%>,<%=pid%>,'<%=name%>','<%=url%>','<%=title%>','<%=target%>');
                           <%
                              }
                            %>
                         document.writeln(d);
                         </script>
 					 </div>