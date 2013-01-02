<?php
class ImmutableDateTime
{
	private $_dt;

	public function __construct($time = 'now', DateTimeZone $timezone = NULL)
	{
		$this->_dt = new DateTime($time, $timezone);
	}

	 public static function createFromFormat($format, $time, $timezone = NULL)
	 {
		$dt  = call_user_func_array(array('DateTime', 'createFromFormat'),
									func_get_args());
		$idt = new self();
		$idt->_setDateTimeObject($dt);
		return $idt;
	 }

	public function add(DateInterval $interval)
	{
		$clone = clone($this->_dt);
		$clone->add($interval);
		return $clone;
	}

	public function sub(DateInterval $interval)
	{
		$clone = clone($this->_dt);
		$clone->sub($interval);
		return $clone;
	}

	public function diff(self $that)
	{
		return $this->_dt->diff($that->_getDateTimeObject());
	}

	 public function getTimestamp()
	 {
		return $this->_dt->getTimestamp();
	 }

	public function getTimezone()
	{
		return $this->_dt->getTimezone();
	}

	public function getOffset()
	{
		return $this->_dt->getOffset();
	}

	public function format($format)
	{
		return $this->_dt->format($format);
	}

	private function _getDateTimeObject()
	{
		return $this->_dt;
	}

	private function _setDateTimeObject(DateTime $datetime)
	{
		$this->_dt = $datetime;
	}

    public function setDate($year, $month, $day) {
        throw new ImmutableException();
    }
    public function setISODate($year, $week, $day=null) {
        throw new ImmutableException();
    }
    public function setTime($hour, $minute, $second=null) {
        throw new ImmutableException();
    }
    public function setTimestamp($timestamp) {
        throw new ImmutableException();
    }
    public function setTimezone($timezone) {
        throw new ImmutableException();
    }

}

class ImmutableException extends \Exception
{
    public function __construct()
    {
        parent::__construct("Cannot modify DateTime\DateTime instance, its immutable!");
    }
}

$dt = new ImmutableDateTime('2011-09-21');
print $dt->getTimestamp();

?>