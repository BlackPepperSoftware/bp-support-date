/*
 * Copyright 2014 Black Pepper Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.blackpepper.support.date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import static uk.co.blackpepper.support.date.Dates.newDate;

public class DatesTest {

	@Test
	public void newDateWithYearMonthDayReturnsDate() {
		Date actual = Dates.newDate(1970, 1, 1);
		
		assertThat(actual.toString(), is("Thu Jan 01 00:00:00 GMT 1970"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newDateWithYearMonthDayWithInvalidDayThrowsException() {
		Dates.newDate(1970, 1, 32);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newDateWithYearMonthDayWithInvalidMonthThrowsException() {
		Dates.newDate(1970, 13, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newDateWithYearMonthDayWithInvalidDateThrowsException() {
		Dates.newDate(1970, 2, 31);
	}
	
	@Test
	public void newDateWithYearMonthDayHourMinsSecondsReturnsDate() {
		Date actual = Dates.newDate(1970, 1, 1, 1, 1, 1);
		
		assertThat(actual.toString(), is("Thu Jan 01 01:01:01 GMT 1970"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newDateWithYearMonthDayHourMinsSecondsWithInvalidHoursThrowsException() {
		Dates.newDate(1970, 1, 1, 25, 0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newDateWithYearMonthDayHourMinsSecondsWithInvalidMinutesThrowsException() {
		Dates.newDate(1970, 1, 1, 0, 61, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newDateWithYearMonthDayHourMinsSecondsWithInvalidSecondsThrowsException() {
		Dates.newDate(1970, 1, 1, 0, 0, 61);
	}
	
	@Test
	public void newDateWithYearMonthDayHourMinsSecondsMillisReturnsDate() {
		Date actual = Dates.newDate(1970, 1, 1, 0, 0, 0, 1);
		
		assertThat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(actual), is("1970-01-01 00:00:00.001"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void newDateWithYearMonthDayHourMinsSecondsMillisWithInvalidMillisThrowsException() {
		Dates.newDate(1970, 1, 1, 0, 0, 0, 1001);
	}

	@Test
	public void removeMillisWithZeroReturnsNoMillis() {
		assertThat(Dates.removeMillis(new Date(0)), is(new Date(0)));
	}
	
	@Test
	public void removeMillisWithOneReturnsNoMillis() {
		assertThat(Dates.removeMillis(new Date(1)), is(new Date(0)));
	}
	
	@Test
	public void removeMillisWithOneSecondReturnsNoMillis() {
		assertThat(Dates.removeMillis(new Date(1000)), is(new Date(1000)));
	}
	
	@Test
	public void removeMillisWithOneSecondAndOneReturnsNoMillis() {
		assertThat(Dates.removeMillis(new Date(1001)), is(new Date(1000)));
	}
	
	@Test
	public void removeMillisWithNullReturnsNull() {
		assertThat(Dates.removeMillis(null), is(nullValue()));
	}
	
	@Test
	public void copyWithDateReturnsDate() {
		Date actual = Dates.copy(newDate(2000, 1, 1));
		
		assertThat(actual, is(newDate(2000, 1, 1)));
	}
	
	@Test
	public void copyWithDateReturnsDifferentInstance() {
		Date date = newDate(2000, 1, 1);
		
		Date actual = Dates.copy(date);
		
		assertThat(actual, is(not(sameInstance(date))));
	}
	
	@Test
	public void copyWithNullReturnsNull() {
		assertThat(Dates.copy(null), is(nullValue()));
	}
	
	@Test
	public void toSqlTimestampWithDateReturnsTimestamp() {
		assertThat(Dates.toSqlTimestamp(new Date(123)), is(new Timestamp(123)));
	}
	
	@Test
	public void toSqlTimestampWithNullReturnsNull() {
		assertThat(Dates.toSqlTimestamp(null), is(nullValue()));
	}
}
