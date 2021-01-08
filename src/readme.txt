
  购物车列表(cart.jsp)--->结算（预处理 getOrderView  查收货地址  和购物车）
  -->order.jsp(选择收货地址)--->确认购买--->生成订单(createOrder) （
  1）创建一个订单对象，并插入数据库
  2)根据用户的编号查询购物车商品并且把商品信息插入到订单详情表
  3）删除购物车）
  --->查询用户的订单列表(showAllOrder)---->orderList.jsp