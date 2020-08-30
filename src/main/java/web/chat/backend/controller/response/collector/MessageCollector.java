package web.chat.backend.controller.response.collector;

import static java.util.stream.Collector.Characteristics.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import web.chat.backend.controller.response.MessageResponse;
import web.chat.backend.controller.response.MessagesResponse;
import web.chat.backend.entity.Message;

/**
 * Created by koseungbin on 2020-08-15
 *
 * reference by : https://blog.indrek.io/articles/creating-a-collector-in-java-8/
 */

public class MessageCollector implements Collector<Message, List<MessageResponse>, MessagesResponse> {

	@Override
	public Supplier<List<MessageResponse>> supplier() {
		return ArrayList::new;
	}

	@Override
	public BiConsumer<List<MessageResponse>, Message> accumulator() {
		return (list, message) -> {
			System.out.println(new MessageResponse(message));
			list.add(new MessageResponse(message));
		};
	}

	@Override
	public BinaryOperator<List<MessageResponse>> combiner() {
		// parallelStream 사용시 호출
		return (e1, e2) -> Stream.of(e1, e2)
			.flatMap(Collection::stream)
			.collect(Collectors.toList());
	}

	@Override
	public Function<List<MessageResponse>, MessagesResponse> finisher() {
		return MessagesResponse::new;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(CONCURRENT));
	}
}
