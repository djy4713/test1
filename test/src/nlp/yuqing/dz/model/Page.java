package nlp.yuqing.dz.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 文档对象
 */
public class Page implements Serializable {

    private static final long serialVersionUID = -6737392177067993535L;
    private Integer id;
    private String url;
    private String fn;
    private String webSite;
    private Date downloadDate;
    private String title;
    private String summary;
    private int clickCount;
    private int replyCount;
    private int forwardCount;
    private int type;
    private Date publishDate;
    private int newsLevel;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }
    
   
    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

   

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getForwardCount() {
        return forwardCount;
    }

    public void setForwardCount(int forwardCount) {
        this.forwardCount = forwardCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }


	public void setNewsLevel(int newsLevel) {
		this.newsLevel = newsLevel;
	}

	public int getNewsLevel() {
		return newsLevel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getDownloadDate() {
		return downloadDate;
	}

	public void setDownloadDate(Date downloadDate) {
		this.downloadDate = downloadDate;
	}
	
}
