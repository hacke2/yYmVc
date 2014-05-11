package edu.cqut.filter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cqut.util.ActionConfig;
import edu.cqut.util.BeanUtil;
import edu.cqut.util.ConfigUtil;
import edu.cqut.util.PropertiesUtil;
 
 
/**
 * MVC��ܵĺ��Ŀ�����
 */
public class FilterDispatcher implements Filter {
 
    private Map<String, ActionConfig> actionMap = new HashMap<String, ActionConfig>();
    public void init(FilterConfig filterConfig) throws ServletException {
       // �õ���ܺ��������ļ�mvc1.xml��web�������ϵ�����·����Ȼ�����XML
       String webPath = getClass().getClassLoader().getResource("mvc.xml")
              .getPath();
       ConfigUtil.parseConfigFile(webPath, actionMap);
    }
 
    @SuppressWarnings("static-access")
    public void doFilter(ServletRequest req, ServletResponse res,
           FilterChain chain) throws IOException, ServletException {
 
       // ���HTTPЭ��,���������Ӧ����ԭΪHTTP����
       HttpServletRequest request = (HttpServletRequest) req;
       HttpServletResponse response = (HttpServletResponse) res;
 
       // �����������Ӧ�ı��뷽ʽ����������
       request.setCharacterEncoding("utf-8");
       response.setCharacterEncoding("utf-8");
 
       // �õ�request������·��
       String url = request.getServletPath();
 
       // �����׺��Ϊ.action,����Ҫ�ú���Filter���ƣ�ֱ�ӹ���
 
       if (!url.endsWith(PropertiesUtil.getValue("action-suffix"))) {
           chain.doFilter(request, response);
           return;
       }
       // ����Request��·��
       int start = url.indexOf("/");
       int end = url.lastIndexOf(".");
       String path = url.substring(start + 1, end);
 
       //��path�ҵ����Ӧ��ActionConfig�࣬���������������Action�������Ϣ
       ActionConfig config = actionMap.get(path);
 
       // ƥ�䲻�ɹ��ͷ����Ҳ���ҳ�������Ϣ
       if (config == null) {
           response.setStatus(response.SC_NOT_FOUND);
           return;
       }
 
       //��ActionConfig�õ�Action�����������
       String clzActionName = config.getClzName();
       // ʵ����Action����, �����ڸ���������ʾ
       Object action = BeanUtil.getAction(clzActionName);
       if (action == null) {
           response.setStatus(response.SC_NOT_FOUND);
           return;
       }
 
       // ǰ������,ȡ��request����Ĳ���������Action��set��������������ֵ
       BeanUtil.requestToAction(request, action);
       // ִ��Action���޲η���������execute()
       String result = BeanUtil.executeAction(config, action);
 
       // ��������,����Action��get����,������Action�����Է��õ�request����
       BeanUtil.actionToRequest(request, action);
 
       // ������Ӧ��JSPҳ��
       if (result == null) {
           response.setStatus(response.SC_NOT_FOUND);
           return;
       }
       //ת�����µĵ�ַ
       request.getRequestDispatcher(result).forward(request, response);
    }
 
    public void destroy() {
       // TODO Auto-generated method stub
    }
 
}
