import PropTypes from 'prop-types';

export default PropTypes.shape({
  id: PropTypes.number,

  name: PropTypes.string,
  location: PropTypes.string,
  date: PropTypes.string,
});

export const formValues = PropTypes.shape({
  name: PropTypes.string,
  location: PropTypes.string,
  date: PropTypes.oneOfType([PropTypes.string, PropTypes.instanceOf(Date)]),
});

export const formTouched = PropTypes.shape({
  name: PropTypes.oneOfType([PropTypes.bool, PropTypes.shape()]),
  location: PropTypes.oneOfType([PropTypes.bool, PropTypes.shape()]),
  date: PropTypes.oneOfType([PropTypes.bool, PropTypes.shape()]),
});

export const formErrors = PropTypes.shape({
  name: PropTypes.oneOfType([PropTypes.string, PropTypes.shape()]),
  location: PropTypes.oneOfType([PropTypes.string, PropTypes.shape()]),
  date: PropTypes.oneOfType([PropTypes.string, PropTypes.shape()]),
});
