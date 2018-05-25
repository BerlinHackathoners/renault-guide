package com.renault.guide.knowledge.domain;

import lombok.Data;

@Data
public class ApiUrls
{
	private String summary;
	private String talk_page_html;
	private String edit_html;
	private String references;
	private String media;
	private String metadata;

	@Override
	public String toString()
	{
		return "ClassPojo [summary = "+summary+", talk_page_html = "+talk_page_html+", edit_html = "+edit_html+", references = "+references+", media = "+media+", metadata = "+metadata+"]";
	}
}
