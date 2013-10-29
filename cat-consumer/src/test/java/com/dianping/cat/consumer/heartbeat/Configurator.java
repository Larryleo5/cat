package com.dianping.cat.consumer.heartbeat;

import java.util.ArrayList;
import java.util.List;

import org.unidal.lookup.configuration.AbstractResourceConfigurator;
import org.unidal.lookup.configuration.Component;

import com.dianping.cat.consumer.MockReportManager;
import com.dianping.cat.service.ReportDelegate;
import com.dianping.cat.service.ReportManager;

public class Configurator extends AbstractResourceConfigurator {

	public static void main(String[] args) {
		generatePlexusComponentsXmlFile(new Configurator());
	}

	protected Class<?> getTestClass() {
		return HeartbeatAnalyzerTest.class;
	}

	@Override
	public List<Component> defineComponents() {
		List<Component> all = new ArrayList<Component>();
		final String ID = HeartbeatAnalyzer.ID;

		all.add(C(ReportManager.class, ID, MockReportManager.class)//
		      .req(ReportDelegate.class, ID, "m_delegate"));
		all.add(C(ReportDelegate.class, ID, ExtendedHeartbeatDelegate.class));

		return all;
	}

	public static class ExtendedHeartbeatDelegate extends HeartbeatDelegate {
	}
}